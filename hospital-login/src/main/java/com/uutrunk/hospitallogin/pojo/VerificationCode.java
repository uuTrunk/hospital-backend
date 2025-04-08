package com.uutrunk.hospitallogin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("verification_code")
public class VerificationCode {
    private Long codeId;

    private String phoneNumber;

    private String code;

    private Date createTime;

    private Boolean isUsed;
}