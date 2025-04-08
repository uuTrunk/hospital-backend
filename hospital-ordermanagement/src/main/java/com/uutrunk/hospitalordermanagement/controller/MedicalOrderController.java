package com.uutrunk.hospitalordermanagement.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uutrunk.hospitalordermanagement.common.ApiResponse;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderCreateDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderQueryDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderUpdateDTO;
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
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody MedicalOrderCreateDTO dto) {
        MedicalOrderDTO result = medicalOrderService.create(dto);
        return ResponseEntity.ok(ApiResponse.success(result));
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

    @DeleteMapping("/void")
    public ResponseEntity<ApiResponse<?>> voidOrder(@RequestParam("orderId") String orderId,
                                                    @RequestParam("operatorId") Integer operatorId) {
        medicalOrderService.voidOrder(orderId, operatorId);
        return ResponseEntity.ok(ApiResponse.success("医嘱作废成功"));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> list(
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
        IPage<MedicalOrderDTO> pageResult = medicalOrderService.list(query);
        return ResponseEntity.ok(ApiResponse.success(pageResult));
    }

    @GetMapping("/print")
    public ResponseEntity<ApiResponse<?>> print(@RequestParam("orderIds") String orderIds) {
        // 暂时返回成功响应，实际应调用打印服务
        return ResponseEntity.ok(ApiResponse.success("打印任务已发起"));
    }
}