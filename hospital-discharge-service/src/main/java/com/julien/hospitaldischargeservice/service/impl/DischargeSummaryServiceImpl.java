package com.julien.hospitaldischargeservice.service.impl;

import com.julien.hospitaldischargeservice.entity.DischargeSummary;
import com.julien.hospitaldischargeservice.repository.DischargeSummaryRepository;
import com.julien.hospitaldischargeservice.service.DischargeSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DischargeSummaryServiceImpl implements DischargeSummaryService {

    private final DischargeSummaryRepository dischargeSummaryRepository;

    @Override
    public DischargeSummary getDischargeSummaryByDischargeId(Integer dischargeId) {
        return dischargeSummaryRepository.findByDischargeMain_DischargeId(dischargeId);  // 通过离院记录 ID 查询离院小结
    }
}
