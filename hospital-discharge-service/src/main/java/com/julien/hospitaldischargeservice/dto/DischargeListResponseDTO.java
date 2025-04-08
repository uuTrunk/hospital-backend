package com.julien.hospitaldischargeservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class DischargeListResponseDTO {
    private long total;
    private List<DischargeListItemDTO> list;
}
