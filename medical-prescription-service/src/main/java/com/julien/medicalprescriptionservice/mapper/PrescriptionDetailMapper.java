package com.julien.medicalprescriptionservice.mapper;

import com.julien.medicalprescriptionservice.pojo.PrescriptionDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrescriptionDetailMapper {

    List<PrescriptionDetail> selectByPrescriptionId(@Param("prescriptionId") String prescriptionId);
}
