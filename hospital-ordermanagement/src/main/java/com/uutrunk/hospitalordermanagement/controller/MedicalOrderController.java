package com.uutrunk.hospitalordermanagement.controller;

import com.uutrunk.hospitalordermanagement.common.ApiResponse;
import com.uutrunk.hospitalordermanagement.dto.*;
import com.uutrunk.hospitalordermanagement.service.MedicalOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-order")
public class MedicalOrderController {

    @Autowired
    private MedicalOrderService medicalOrderService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> create(@RequestBody MedicalOrderCreateDTO dto) {
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
                                                    @RequestParam("doctorId") Integer doctorId) {
        medicalOrderService.deleteOrder(orderId, doctorId);
        return ResponseEntity.ok(ApiResponse.success("医嘱作废成功"));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageResult<MedicalOrderDTO>>> list(
            @RequestParam(required = false) String orderType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String patientName,
            @RequestParam int page,
            @RequestParam int pageSize) {
        MedicalOrderQueryDTO query = new MedicalOrderQueryDTO();
        query.setOrderType(orderType);
        query.setStatus(status);
        query.setPatientName(patientName);
        query.setPage(page);
        query.setPageSize(pageSize);
//        System.out.println(query);
        PageResult<MedicalOrderDTO> pageResult = medicalOrderService.list(query);
        return ResponseEntity.ok(ApiResponse.success(pageResult));
    }

    @GetMapping("/print")
    public ResponseEntity<ApiResponse<?>> print(@RequestParam("orderIds") String orderIds) {
        // 暂时返回成功响应，实际应调用打印服务
        return ResponseEntity.ok(ApiResponse.success("打印任务已发起"));
    }

    @GetMapping("/chat")
    public ResponseEntity<ApiResponse<?>> chat(@RequestParam("orderId") String message) {
        // 暂时返回成功响应，实际应调用聊天服务
        return ResponseEntity.ok(ApiResponse.success(medicalOrderService.chat(message)));
    }
}