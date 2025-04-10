package com.uutrunk.hospitalordermanagement.service;

import com.uutrunk.hospitalordermanagement.dto.MedicalOrderCreateDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderQueryDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderUpdateDTO;
import com.uutrunk.hospitalordermanagement.pojo.MedicalOrderMain;

public interface MedicalOrderService {

    /**
     * 创建医嘱
     * @param dto 医嘱创建参数
     * @return 创建的医嘱Id
     */
    String create(MedicalOrderCreateDTO dto);

    /**
     * 更新医嘱信息
     * @param dto 医嘱更新参数
     * @return 更新结果
     */
    void update(MedicalOrderUpdateDTO dto);

    /**
     * 作废医嘱
     * @param orderId 医嘱ID
     * @param operatorId 操作人ID
     * @return 作废结果
     */
    void deleteOrder(String orderId, Integer operatorId);

    /**
     * 获取医嘱详情
     * @param orderId 医嘱ID
     * @return 医嘱详情
     */
    MedicalOrderDTO getById(String orderId);

    /**
     * 分页查询医嘱列表
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    IPage<MedicalOrderDTO> list(MedicalOrderQueryDTO queryDTO);
}