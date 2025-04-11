package com.uutrunk.hospitalordermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uutrunk.hospitalordermanagement.pojo.DoctorInfo;

/**
* @author uutrunk
* @description 针对表【doctor_info】的数据库操作Mapper
* @createDate 2025-04-11 11:10:32
* @Entity com.uutrunk.hospitalordermanagement.pojo.DoctorInfo
*/
public interface DoctorInfoMapper extends BaseMapper<DoctorInfo> {

    int deleteByPrimaryKey(Long id);

    int insert(DoctorInfo record);

    int insertSelective(DoctorInfo record);

    DoctorInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DoctorInfo record);

    int updateByPrimaryKey(DoctorInfo record);

}
