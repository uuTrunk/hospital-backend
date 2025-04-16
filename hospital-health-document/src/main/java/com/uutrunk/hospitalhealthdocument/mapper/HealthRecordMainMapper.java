package com.uutrunk.hospitalhealthdocument.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uutrunk.hospitalhealthdocument.pojo.HealthRecordMain;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.remoting.http12.rest.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 修改HealthRecordMainMapper添加自定义查询
public interface HealthRecordMainMapper extends BaseMapper<HealthRecordMain> {
    // 需要实现联表查询的自定义方法
    @Select("SELECT hr.*, p.name AS patient_name " +
            "FROM health_record_main hr " +
            "LEFT JOIN patient_info p ON hr.patient_id = p.patient_id " +
            "WHERE ...")
    // 需要根据查询条件补充
    Page<HealthRecordMain> selectWithPatient(Page<HealthRecordMain> page, @Param("ew") QueryWrapper<HealthRecordMain> wrapper);

    @Select("SELECT * FROM health_record_main")
    List<HealthRecordMain> selectAll();
}