package com.julien.hospitaldischargeservice.repository;

import com.julien.hospitaldischargeservice.entity.DischargeHandover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DischargeHandoverRepository extends JpaRepository<DischargeHandover, Integer> {

    // 通过 discharge_id 查找交接记录（可根据需求添加更多查询方法）
}
