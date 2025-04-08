package com.julien.hospitaldischargeservice.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/discharge/handover")
public class DischargeHandoverController {

    @PostMapping("/confirm")
    public Map<String, Object> confirmHandover(@RequestBody Map<String, Object> requestBody) {
        // 取出 dischargeId，暂时不进行任何业务处理
        Integer dischargeId = (Integer) requestBody.get("dischargeId");

        // 后续可在此加入数据库记录、权限校验、提交时间保存等功能

        return Map.of(
                "code", 200,
                "message", "交接确认成功"
        );
    }
}
