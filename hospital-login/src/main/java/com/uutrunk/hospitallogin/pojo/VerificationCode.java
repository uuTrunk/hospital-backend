package com.uutrunk.hospitallogin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("verification_code")
public class VerificationCode {
    @TableId(value = "code_id", type = IdType.AUTO)
    private Long codeId;

    @TableField(value = "phone_number")
    private String phoneNumber;

    private String code;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "is_used")
    private Boolean isUsed;
}