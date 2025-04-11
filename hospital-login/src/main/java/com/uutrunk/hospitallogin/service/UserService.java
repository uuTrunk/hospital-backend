package com.uutrunk.hospitallogin.service;

import com.uutrunk.hospitallogin.common.ApiResponse;
import com.uutrunk.hospitallogin.dto.*;

public interface UserService {
    public UserDTO createUser(UserDTO dto);
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    public void resetPassword(ResetPasswordDTO dto);
    public RolePermissionDTO getPermissions(String token);
    public void updateUserStatus(UpdateUserStatusDTO dto);
    public void sendVerificationCode(VerificationCodeRequestDTO dto);
}