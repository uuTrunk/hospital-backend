package com.uutrunk.hospitallogin.service;

import com.uutrunk.hospitallogin.common.ApiResponse;
import com.uutrunk.hospitallogin.dto.*;

public interface UserService {
    public ApiResponse<UserDTO> createUser(UserDTO dto);
    public ApiResponse<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);
    public ApiResponse<?> resetPassword(ResetPasswordDTO dto);
    public ApiResponse<RolePermissionDTO> getPermissions(String role);
    public ApiResponse<?> updateUserStatus(UpdateUserStatusDTO dto);
    public ApiResponse<?> sendVerificationCode(VerificationCodeRequestDTO dto);
}