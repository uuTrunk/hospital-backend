package com.uutrunk.hospitallogin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uutrunk.hospitallogin.dto.*;
import com.uutrunk.hospitallogin.exception.*;
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
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
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
    public UserDTO createUser(UserDTO dto) {

        // 检查账号唯一性
        QueryWrapper<User> accountCheck = new QueryWrapper<>();
        accountCheck.eq("account", dto.getAccount());
        if (userMapper.exists(accountCheck)) {
            throw new RuntimeException("账号已存在");
        }
        else if(!check(dto.getAdminPassword())) {
            throw new RuntimeException("权限验证失败");
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

        return UserDTO.fromEntity(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = null;
        try {
            QueryWrapper<User> query = new QueryWrapper<>();
            query.eq("account", loginRequestDTO.getAccount());
            user = userMapper.selectOne(query);
        }
        catch (RuntimeException e) {
            throw new DataBaseException("用户获取失败");
        }
        
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        
        if (!PasswordUtil.verify(loginRequestDTO.getPassword(), user.getPassword())) { // 替换为工具类调用
            throw new PasswordErrorException("密码错误");
        }
        
        if ("禁用".equals(user.getStatus())) {
            throw new UserBannedException("账户已被禁用");
        }
        
        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(user.getUserId());
        response.setRole(user.getRole());

        // 从配置读取Base64密钥并解码
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // 移除Base64解码

        // 生成JWT Token逻辑
        String token = Jwts.builder()
            .setSubject(user.getUserId().toString())
            .claim("role", user.getRole())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact();
        response.setToken(token);
        
        return response;
    }

    @Transactional
    @Override
    public void resetPassword(ResetPasswordDTO dto) {
        VerificationCode code = verificationCodeMapper.selectOne(
            new QueryWrapper<VerificationCode>()
                .eq("phone_number", dto.getPhoneNumber())
                .eq("code", dto.getVerificationCode())
                .ge("create_time", new Date(System.currentTimeMillis() - 5*60*1000))
                .eq("is_used", false)
        );
        
        if (code == null) {
            throw new VerifyError("验证码错误或已过期");
        }
        
        User user = userMapper.selectOne(
            new QueryWrapper<User>().eq("phone_number", dto.getPhoneNumber())
        );
        
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        
        user.setPassword(PasswordUtil.encode(dto.getNewPassword()));
        userMapper.updateById(user);
        
        code.setIsUsed(true);
        verificationCodeMapper.updateById(code);
        
        return;
    }

    @Override
    public RolePermissionDTO getPermissions(String token) {
        // 1. 解析Token获取用户信息
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // 移除Base64解码
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new InvalidTokenException("无效的Token");
        }

        // 2. 验证用户存在性
        String userIdStr = claimsJws.getBody().getSubject();
//        System.out.println(userIdStr);
        User user = userMapper.selectById(Integer.valueOf(userIdStr));
        System.out.println(user);
//        System.out.println("success");
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 3. 获取角色并查询权限
        String role = user.getRole();
        System.out.println(role);
        if (role == null) {
            throw new RoleNotFoundException("角色不存在");
        }
        String perimissions = rolePermissionMapper.selectOne(new QueryWrapper<RolePermission>()
                .eq("role", role))
                .getPermissions();
        System.out.println("success");

        // 4. 构建返回对象
        RolePermissionDTO dto = new RolePermissionDTO();
        dto.setRole(role);
        dto.setPermissions(List.of(perimissions.split(",")));
        return dto;
    }


    @Transactional
    @Override
    public void sendVerificationCode(VerificationCodeRequestDTO dto) {
        // 1. 验证手机号是否已注册
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("phone_number", dto.getPhoneNumber()));
        if (user == null) {
            throw new PhonenumberNotRegistedException("手机号未注册");
        }

        // 2. 检查5分钟内是否已发送过验证码
        VerificationCode latestCode = verificationCodeMapper.selectOne(new QueryWrapper<VerificationCode>()
                .eq("phone_number", dto.getPhoneNumber())
                .ge("create_time", new Date(System.currentTimeMillis() - 5 * 60 * 1000))
                .eq("is_used", false));
        if (latestCode != null) {
            throw new RepeatedSendVerificationCodeException("请勿重复发送验证码");
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

        return;
    }

    @Transactional
    @Override
    public void updateUserStatus(UpdateUserStatusDTO dto) {
        User user = userMapper.selectById(dto.getUserId());
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        
        user.setStatus(dto.getStatus());
        userMapper.updateById(user);
        
        return;
    }
}