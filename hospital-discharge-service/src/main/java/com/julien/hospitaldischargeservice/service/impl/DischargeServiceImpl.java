package com.julien.hospitaldischargeservice.service.impl;

import com.julien.hospitaldischargeservice.entity.DischargeMain;
import com.julien.hospitaldischargeservice.repository.DischargeMainRepository;
import com.julien.hospitaldischargeservice.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DischargeServiceImpl implements DischargeService {

    @Autowired
    private DischargeMainRepository dischargeMainRepository;

    @Override
    public Page<DischargeMain> getDischargeList(LocalDate startDate, LocalDate endDate, String nameOrCode, Pageable pageable) {
        return dischargeMainRepository.findDischarges(startDate, endDate, nameOrCode, pageable);
    }
}
