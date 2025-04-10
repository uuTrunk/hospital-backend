package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.PrescriptionSendRequest;
import com.julien.medicalprescriptionservice.dto.PrescriptionSendResponse;
import com.julien.medicalprescriptionservice.service.PrescriptionService;
import com.julien.medicalprescriptionservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionSendController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/api/prescription/send")
    public ApiResponse<PrescriptionSendResponse> sendPrescription(@RequestBody PrescriptionSendRequest request) {
        String message = prescriptionService.sendPrescription(request.getPrescriptionId());
        PrescriptionSendResponse response = new PrescriptionSendResponse();
        response.setMessage(message);
        return ApiResponse.success(response);
    }
}
