package com.uutrunk.hospitallogin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_info")
public class User {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String account;

    private String password;

    private String role;

    private String name;

    private String status;

    private Date createTime;

    private Long creatorId;

    private String phoneNumber;
}