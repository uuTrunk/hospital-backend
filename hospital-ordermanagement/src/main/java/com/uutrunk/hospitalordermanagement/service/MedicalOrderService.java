package com.uutrunk.hospitalordermanagement.service;

import com.uutrunk.hospitalordermanagement.dto.*;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

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
     * @param doctorName  医生ID
     * @return 作废结果
     */
    void deleteOrder(String orderId, String doctorName);

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
    PageResult<MedicalOrderDTO> list(MedicalOrderQueryDTO queryDTO);

    String chat(String message);

}