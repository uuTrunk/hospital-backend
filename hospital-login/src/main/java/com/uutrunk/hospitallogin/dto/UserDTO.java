package com.uutrunk.hospitallogin.dto;

import com.uutrunk.hospitallogin.pojo.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    private String account;
    private String password;
    private String role;
    private String name;

    // 从实体类转换的静态方法
    public static UserDTO fromEntity(User user) {
        return new UserDTO()
            .setAccount(user.getAccount())
            .setName(user.getName())
            .setRole(user.getRole());
    }
}