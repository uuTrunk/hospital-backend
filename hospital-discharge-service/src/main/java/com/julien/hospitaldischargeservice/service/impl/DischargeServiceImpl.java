package com.julien.hospitaldischargeservice.service.impl;

import com.julien.hospitaldischargeservice.dto.DischargeDTO;
import com.julien.hospitaldischargeservice.entity.DischargeMain;
import com.julien.hospitaldischargeservice.repository.DischargeMainRepository;
import com.julien.hospitaldischargeservice.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DischargeServiceImpl implements DischargeService {

    @Autowired
    private DischargeMainRepository dischargeMainRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<DischargeDTO> getDischargeList(LocalDate startDate, LocalDate endDate, 
                                              String nameOrCode, Pageable pageable) {
        return dischargeMainRepository.findDischarges(startDate, endDate, nameOrCode, pageable)
                .map(DischargeDTO::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DischargeDTO> getAllDischarges() {
        List<DischargeMain> discharges = dischargeMainRepository.findAll();
        return discharges.stream()
                .map(DischargeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DischargeDTO getDischargeById(Integer dischargeId) {
        return dischargeMainRepository.findById(dischargeId)
                .map(DischargeDTO::fromEntity)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public DischargeDTO getDischargeByPatientId(Integer patientId) {
        return dischargeMainRepository.findByPatientInfo_PatientId(patientId)
                .map(DischargeDTO::fromEntity)
                .orElse(null);
    }
}
