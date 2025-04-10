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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(userService.login(loginRequestDTO)));
    }

    // 获取权限接口
    @GetMapping("/get-permissions")
    public ResponseEntity<ApiResponse<RolePermissionDTO>> getPermissions(@RequestParam("token") String token) {
        String role = parseRoleFromToken(token);
        return ResponseEntity.ok(ApiResponse.success(userService.getPermissions(role)));
    }

    // 注册接口
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody UserDTO dto) {
        userService.createUser(dto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 修改用户状态接口
    @PutMapping("/update-user-status")
    public ResponseEntity<ApiResponse<Void>> updateUserStatus(@RequestBody UpdateUserStatusDTO dto) {
        userService.updateUserStatus(dto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 获取验证码接口
    @PostMapping("/get-verification-code")
    public ResponseEntity<ApiResponse<Void>> sendVerificationCode(@RequestBody VerificationCodeRequestDTO dto) {
        userService.sendVerificationCode(dto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 重置密码接口
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@RequestBody ResetPasswordDTO dto) {
        userService.resetPassword(dto);
        return ResponseEntity.ok(ApiResponse.success());
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