package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.PrintRequest;
import com.julien.medicalprescriptionservice.dto.PrintResponse;
import com.julien.medicalprescriptionservice.service.PrintService;
import com.julien.medicalprescriptionservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintController {

    @Autowired
    private PrintService printService;

    @GetMapping("/api/print")
    public ApiResponse<PrintResponse> print(@RequestBody PrintRequest request) {
        // 调用服务层的打印业务逻辑
        String message = printService.print(request.getRecordId(), request.getPrescriptionId());

        // 封装响应结果
        PrintResponse response = new PrintResponse();
        response.setMessage(message);

        return ApiResponse.success(response);
    }
}
