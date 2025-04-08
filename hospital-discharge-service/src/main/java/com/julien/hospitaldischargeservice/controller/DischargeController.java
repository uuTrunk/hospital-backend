package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.dto.DischargeListResponseDTO;
import com.julien.hospitaldischargeservice.dto.DischargeListItemDTO;
import com.julien.hospitaldischargeservice.entity.DischargeMain;
import com.julien.hospitaldischargeservice.service.DischargeService;
import com.julien.hospitaldischargeservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/discharge")
@RequiredArgsConstructor
public class DischargeController {

    private final DischargeService dischargeService;

    @GetMapping("/list")
    public ApiResponse<DischargeListResponseDTO> getDischargeList(
            @RequestParam(required = false) String start_date,
            @RequestParam(required = false) String end_date,
            @RequestParam(required = false) String name_or_code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int page_size
    ) {
        LocalDate startDate = parseDate(start_date);
        LocalDate endDate = parseDate(end_date);

        name_or_code = (name_or_code != null && !name_or_code.trim().isEmpty()) ? name_or_code : null;

        int validPageSize = Math.min(Math.max(page_size, 1), 100);
        int validPage = Math.max(page, 0);
        PageRequest pageRequest = PageRequest.of(validPage, validPageSize);

        Page<DischargeMain> dischargeList = dischargeService.getDischargeList(startDate, endDate, name_or_code, pageRequest);

        // 将 DischargeMain 转换为 DischargeListItemDTO
        List<DischargeListItemDTO> dischargeListItems = dischargeList.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // 封装响应
        DischargeListResponseDTO response = new DischargeListResponseDTO();
        response.setTotal(dischargeList.getTotalElements());
        response.setList(dischargeListItems);

        return new ApiResponse<>(200, "成功", response);
    }

    private LocalDate parseDate(String dateStr) {
        try {
            return (dateStr != null) ? LocalDate.parse(dateStr) : null;
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format: " + dateStr);
        }
    }

    private DischargeListItemDTO convertToDTO(DischargeMain dischargeMain) {
        DischargeListItemDTO dto = new DischargeListItemDTO();
        BeanUtils.copyProperties(dischargeMain, dto);
        return dto;
    }
}
