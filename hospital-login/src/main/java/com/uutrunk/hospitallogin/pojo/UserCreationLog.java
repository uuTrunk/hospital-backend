package com.uutrunk.hospitallogin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_creation_log")
public class UserCreationLog {
    private Long logId;

    private Long userId;

    private Long creatorId;

    private Date createTime;
}