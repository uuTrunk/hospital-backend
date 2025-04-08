package com.julien.hospitaldischargeservice.repository;

import com.julien.hospitaldischargeservice.entity.DischargeSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DischargeSummaryRepository extends JpaRepository<DischargeSummary, Integer> {

    /**
     * 根据 discharge_id 查找对应的离院小结
     */
    DischargeSummary findByDischargeMain_DischargeId(Integer dischargeId);
}
