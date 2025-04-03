package com.uutrunk.hospitalordermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uutrunk.hospitalordermanagement.pojo.MedicalOrderMain;
import com.uutrunk.hospitalordermanagement.service.MedicalOrderMainService;
import com.uutrunk.hospitalordermanagement.mapper.MedicalOrderMainMapper;
import org.springframework.stereotype.Service;

/**
* @author uutrunk
* @description 针对表【medical_order_main(医嘱主表)】的数据库操作Service实现
* @createDate 2025-04-03 10:39:43
*/
@Service
public class MedicalOrderMainServiceImpl extends ServiceImpl<MedicalOrderMainMapper, MedicalOrderMain>
    implements MedicalOrderMainService{
    public IPage<MedicalOrderMain> getMedicalOrderList(String orderType, String status, String patientName, int page, int pageSize) {
        QueryWrapper<MedicalOrderMain> queryWrapper = new QueryWrapper<>();
        if (orderType != null && !orderType.isEmpty()) {
            queryWrapper.eq("order_type", orderType);
        }
        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("status", status);
        }
        if (patientName != null && !patientName.isEmpty()) {
            queryWrapper.like("patient_name", patientName);
        }
        return this.page(new Page<>(page, pageSize), queryWrapper);
    }
}




