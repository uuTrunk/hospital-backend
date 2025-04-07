package com.uutrunk.hospitallogin.service;

import com.uutrunk.hospitallogin.common.ApiResponse;
import com.uutrunk.hospitallogin.dto.*;

public interface UserService {
    public ApiResponse<?> createUser(UserDTO dto);
    public ApiResponse<?> login(LoginRequestDTO loginRequestDTO);
    public ApiResponse<?> resetPassword(ResetPasswordDTO dto);
    public ApiResponse<?> getPermissions(String role);
    public ApiResponse<?> updateUserStatus(UpdateUserStatusDTO dto);
    public ApiResponse<?> sendVerificationCode(VerificationCodeRequestDTO dto);
}