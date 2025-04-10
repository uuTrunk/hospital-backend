package com.julien.medicalprescriptionservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.julien.medicalprescriptionservice.pojo.PrescriptionDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrescriptionDetailMapper extends BaseMapper<PrescriptionDetail> {

    List<PrescriptionDetail> selectByPrescriptionId(@Param("prescriptionId") String prescriptionId);
    // 批量插入
    void insertBatch(List<PrescriptionDetail> details);
}
