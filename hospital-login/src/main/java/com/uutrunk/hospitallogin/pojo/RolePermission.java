package com.uutrunk.hospitallogin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_permission")
public class RolePermission {
    private String role;

    private String permissions;
}