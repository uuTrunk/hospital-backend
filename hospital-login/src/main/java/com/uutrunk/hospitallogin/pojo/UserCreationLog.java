package com.uutrunk.hospitallogin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_creation_log")
public class UserCreationLog {
    @TableId(value = "log_id", type=IdType.AUTO)
    private Long logId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "create_time")
    private Date createTime;
}