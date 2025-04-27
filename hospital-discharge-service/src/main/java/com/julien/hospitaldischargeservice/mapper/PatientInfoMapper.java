package com.julien.hospitaldischargeservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.julien.hospitaldischargeservice.entity.PatientInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PatientInfoMapper extends BaseMapper<PatientInfo> {
    PatientInfo getPatientInfoByDischargeId(@Param("dischargeId") Integer dischargeId);
} 