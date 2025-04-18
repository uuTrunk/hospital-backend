package com.uutrunk.hospitalordermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderQueryDTO;
import com.uutrunk.hospitalordermanagement.pojo.MedicalOrderMain;

public interface MedicalOrderMainMapper extends BaseMapper<MedicalOrderMain> {
    // 自动实现CRUD操作
    IPage<MedicalOrderMain> selectPageWithJoin(IPage<MedicalOrderMain> page, MedicalOrderQueryDTO query);

}