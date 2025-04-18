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
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
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
    public ResponseEntity<ApiResponse<RolePermissionDTO>> getPermissions(@RequestParam String token) {
        try {
            RolePermissionDTO dto = userService.getPermissions(token);
            return ResponseEntity.ok(ApiResponse.success(dto));
        } catch (Exception e) {
            throw new RuntimeException("获取权限错误");
        }
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
    // UserController中的parseRoleFromToken方法需同步修改
    private String parseRoleFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

}