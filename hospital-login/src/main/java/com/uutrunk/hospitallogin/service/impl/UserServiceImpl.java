package com.uutrunk.hospitallogin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uutrunk.hospitallogin.dto.*;
import com.uutrunk.hospitallogin.mapper.RolePermissionMapper;
import com.uutrunk.hospitallogin.mapper.UserCreationLogMapper;
import com.uutrunk.hospitallogin.mapper.UserMapper;
import com.uutrunk.hospitallogin.mapper.VerificationCodeMapper;
import com.uutrunk.hospitallogin.pojo.RolePermission;
import com.uutrunk.hospitallogin.pojo.User;
import com.uutrunk.hospitallogin.pojo.UserCreationLog;
import com.uutrunk.hospitallogin.pojo.VerificationCode;
import com.uutrunk.hospitallogin.service.UserService;
import com.uutrunk.hospitallogin.util.PasswordUtil;
import com.uutrunk.hospitallogin.common.ApiResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserCreationLogMapper userCreationLogMapper;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${adminPassword}")
    private String adminPassword;

    // 新增辅助方法
    private boolean check(String password) {
        return password.equals(adminPassword);
    }

    @Transactional
    @Override
    public ApiResponse<UserDTO> createUser(UserDTO dto) {

        // 检查账号唯一性
        QueryWrapper<User> accountCheck = new QueryWrapper<>();
        accountCheck.eq("account", dto.getAccount());
        if (userMapper.exists(accountCheck)) {
            return (ApiResponse<UserDTO>) ApiResponse.error("账号已存在");
        }
        else if(!check(dto.getAdminPassword())) {
            return (ApiResponse<UserDTO>) ApiResponse.error("管理员密码错误");
        }

        // 创建用户实体
        User user = new User();
        user.setAccount(dto.getAccount());
        user.setPassword(PasswordUtil.encode(dto.getPassword())); // 替换为工具类调用
        user.setRole(dto.getRole());
        user.setName(dto.getName());
        user.setStatus("正常"); // 默认启用状态
        user.setCreateTime(new Date());

        // 保存用户信息
        userMapper.insert(user);

        // 记录用户创建日志
        UserCreationLog log = new UserCreationLog();

        log.setUserId(user.getUserId());
        log.setCreateTime(new Date());
        userCreationLogMapper.insert(log);

        return ApiResponse.success();
    }

    @Override
    public ApiResponse<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("account", loginRequestDTO.getAccount());
        User user = userMapper.selectOne(query);
        
        if (user == null) {
            return (ApiResponse<LoginResponseDTO>) ApiResponse.error("账号不存在");
        }
        
        if (!PasswordUtil.verify(loginRequestDTO.getPassword(), user.getPassword())) { // 替换为工具类调用
            return (ApiResponse<LoginResponseDTO>) ApiResponse.error("密码错误");
        }
        
        if ("禁用".equals(user.getStatus())) {
            return (ApiResponse<LoginResponseDTO>) ApiResponse.error("账号已被禁用");
        }
        
        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(user.getUserId());
        response.setRole(user.getRole());

        // 从配置读取Base64密钥并解码
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));

        // 生成JWT Token逻辑
        String token = Jwts.builder()
            .setSubject(user.getUserId().toString())
            .claim("role", user.getRole())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact();
        response.setToken(token);
        
        return ApiResponse.success(response);
    }

    @Transactional
    @Override
    public ApiResponse<?> resetPassword(ResetPasswordDTO dto) {
        VerificationCode code = verificationCodeMapper.selectOne(
            new QueryWrapper<VerificationCode>()
                .eq("phone_number", dto.getPhoneNumber())
                .eq("code", dto.getVerificationCode())
                .ge("create_time", new Date(System.currentTimeMillis() - 5*60*1000))
                .eq("is_used", false)
        );
        
        if (code == null) {
            return ApiResponse.error("验证码错误或已过期");
        }
        
        User user = userMapper.selectOne(
            new QueryWrapper<User>().eq("phone_number", dto.getPhoneNumber())
        );
        
        if (user == null) {
            return ApiResponse.error("用户不存在");
        }
        
        user.setPassword(PasswordUtil.encode(dto.getNewPassword()));
        userMapper.updateById(user);
        
        code.setIsUsed(true);
        verificationCodeMapper.updateById(code);
        
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<RolePermissionDTO> getPermissions(String role) {
        RolePermission rp = rolePermissionMapper.selectById(role);
        if (rp == null) {
            return (ApiResponse<RolePermissionDTO>) ApiResponse.error("角色不存在");
        }
        
        RolePermissionDTO dto = new RolePermissionDTO();
        dto.setRole(rp.getRole());
        dto.setPermissions(List.of(rp.getPermissions().split(",")));
        
        return ApiResponse.success(dto);
    }

    @Transactional
    @Override
    public ApiResponse<?> sendVerificationCode(VerificationCodeRequestDTO dto) {
        // 1. 验证手机号是否已注册
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("phone_number", dto.getPhoneNumber()));
        if (user == null) {
            return ApiResponse.error("该手机号未注册");
        }

        // 2. 检查5分钟内是否已发送过验证码
        VerificationCode latestCode = verificationCodeMapper.selectOne(new QueryWrapper<VerificationCode>()
                .eq("phone_number", dto.getPhoneNumber())
                .ge("create_time", new Date(System.currentTimeMillis() - 5 * 60 * 1000))
                .eq("is_used", false));
        if (latestCode != null) {
            return ApiResponse.error("验证码已发送，请勿重复请求");
        }

        // 3. 生成6位数字验证码
        String code = String.format("%06d", new Random().nextInt(999999));

        // 4. 构建验证码对象并保存
        VerificationCode newCode = new VerificationCode();
        newCode.setPhoneNumber(dto.getPhoneNumber());
        newCode.setCode(code);
        newCode.setCreateTime(new Date());
        newCode.setIsUsed(false);
        verificationCodeMapper.insert(newCode);

        // 5. 调用短信服务发送验证码（此处需补充短信服务集成）
//         smsService.sendSms(dto.getPhoneNumber(), code); // 需要配置短信服务

        return ApiResponse.success("验证码已发送");
    }

    @Transactional
    @Override
    public ApiResponse<?> updateUserStatus(UpdateUserStatusDTO dto) {
        User user = userMapper.selectById(dto.getUserId());
        if (user == null) {
            return ApiResponse.error("用户不存在");
        }
        
        user.setStatus(dto.getStatus());
        userMapper.updateById(user);
        
        return ApiResponse.success();
    }
}