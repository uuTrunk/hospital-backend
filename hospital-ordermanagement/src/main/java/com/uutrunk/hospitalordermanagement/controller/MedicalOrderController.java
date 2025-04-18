package com.uutrunk.hospitalordermanagement.controller;

import com.uutrunk.hospitalordermanagement.common.ApiResponse;
import com.uutrunk.hospitalordermanagement.dto.*;
import com.uutrunk.hospitalordermanagement.service.MedicalOrderService;
//import org.springframework.ai.openai.OpenAiChatModel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/medical-order")
@CrossOrigin(origins = "*")
public class MedicalOrderController {

    @Autowired
    private MedicalOrderService medicalOrderService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> create(@RequestBody MedicalOrderCreateDTO dto) {
        System.out.println("success");
        ApiResponse<String> response = ApiResponse.success(medicalOrderService.create(dto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<?>> getDetail(@RequestParam("orderId") String orderId) {
        MedicalOrderDTO dto = medicalOrderService.getById(orderId);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> update(@Valid @RequestBody MedicalOrderUpdateDTO dto) {
        medicalOrderService.update(dto);
        return ResponseEntity.ok(ApiResponse.success("医嘱修改成功"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteOrder(@RequestParam("orderId") String orderId,
                                                    @RequestParam("doctorName") String doctorName) {
        medicalOrderService.deleteOrder(orderId, doctorName);
        return ResponseEntity.ok(ApiResponse.success("医嘱作废成功"));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageResult<MedicalOrderDTO>>> list(
            @RequestParam(value = "orderType", required = false) String orderType,
            @RequestParam(value = "status",required = false) String status,
            @RequestParam(value = "patient_name",required = false) String patientName,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        MedicalOrderQueryDTO query = new MedicalOrderQueryDTO();
        query.setOrderType(orderType);
        query.setStatus(status);
        query.setPatientName(patientName);
        query.setPage(page);
        query.setPageSize(pageSize);
//        System.out.println(query);
//        System.out.println(pageSize);
        PageResult<MedicalOrderDTO> pageResult = medicalOrderService.list(query);
        return ResponseEntity.ok(ApiResponse.success(pageResult));
    }

    @GetMapping("/print")
    public ResponseEntity<ApiResponse<?>> print(@RequestParam("orderIds") String orderIds) {
        // 暂时返回成功响应，实际应调用打印服务
        return ResponseEntity.ok(ApiResponse.success("打印任务已发起"));
    }

    /**
     *
     * @param message 问题
     * @return 回答结果
     */
    @GetMapping("/chat")
    public ResponseEntity<ApiResponse<String>> chat(@RequestParam("message") String message) {
        return ResponseEntity.ok(ApiResponse.success(medicalOrderService.chat(message)));
    }
}