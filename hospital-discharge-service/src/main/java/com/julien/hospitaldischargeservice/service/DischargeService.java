package com.julien.hospitaldischargeservice.service;

import com.julien.hospitaldischargeservice.dto.DischargeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface DischargeService {
    /**
     * 获取离院记录列表，支持分页和筛选
     * 
     * @param startDate  开始日期
     * @param endDate    结束日期
     * @param nameOrCode 姓名或编号
     * @param pageable   分页参数
     * @return 离院记录DTO分页对象
     */
    Page<DischargeDTO> getDischargeList(LocalDate startDate, LocalDate endDate,
            String nameOrCode, Pageable pageable);

    /**
     * 获取所有离院记录
     * 
     * @return 离院记录DTO列表
     */
    List<DischargeDTO> getAllDischarges();

    /**
     * 根据ID获取离院记录详情
     * 
     * @param dischargeId 离院记录ID
     * @return 离院记录DTO
     */
    DischargeDTO getDischargeById(Integer dischargeId);

    /**
     * 根据患者ID获取离院记录
     * 
     * @param patientId 患者ID
     * @return 离院记录DTO
     */
    DischargeDTO getDischargeByPatientId(Integer patientId);
}
