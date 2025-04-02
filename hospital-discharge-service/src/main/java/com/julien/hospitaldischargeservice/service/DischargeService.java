package com.julien.hospitaldischargeservice.service;

import com.julien.hospitaldischargeservice.entity.DischargeMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface DischargeService {

    /**
     * 分页查询离院记录
     */
    Page<DischargeMain> getDischargeList(LocalDate startDate, LocalDate endDate, String nameOrCode, Pageable pageable);
}
