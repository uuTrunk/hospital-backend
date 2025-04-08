package com.uutrunk.hospitallogin.controller;

import com.uutrunk.hospitallogin.dto.LoginRequestDTO;
import com.uutrunk.hospitallogin.dto.LoginResponseDTO;
import com.uutrunk.hospitallogin.dto.ResetPasswordDTO;
import com.uutrunk.hospitallogin.dto.UpdateUserStatusDTO;
import com.uutrunk.hospitallogin.dto.UserDTO;
import com.uutrunk.hospitallogin.dto.VerificationCodeRequestDTO;
import com.uutrunk.hospitallogin.dto.RolePermissionDTO;
import com.uutrunk.hospitallogin.service.UserService;
import com.uutrunk.hospitallogin.common.ApiResponse;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/login")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    // 登录接口
    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return (ApiResponse<LoginResponseDTO>) userService.login(loginRequestDTO);
    }

    // 获取权限接口
    @GetMapping("/get-permissions")
    public ApiResponse<RolePermissionDTO> getPermissions(@RequestParam("token") String token) {
        String role = parseRoleFromToken(token);
        return (ApiResponse<RolePermissionDTO>) userService.getPermissions(role);
    }

    // 注册接口
    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    // 修改用户状态接口
    @PutMapping("/update-user-status")
    public ApiResponse<?> updateUserStatus(@RequestBody UpdateUserStatusDTO dto) {
        return userService.updateUserStatus(dto);
    }

    // 获取验证码接口
    @PostMapping("/get-verification-code")
    public ApiResponse<?> sendVerificationCode(@RequestBody VerificationCodeRequestDTO dto) {
        return userService.sendVerificationCode(dto);
    }

    // 重置密码接口
    @PostMapping("/reset-password")
    public ApiResponse<?> resetPassword(@RequestBody ResetPasswordDTO dto) {
        return userService.resetPassword(dto);
    }

    // JWT解析辅助方法
    private String parseRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}