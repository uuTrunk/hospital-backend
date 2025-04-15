package com.uutrunk.hospitalordermanagement.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uutrunk.hospitalestimate.service.HealthAssessmentService;
import com.uutrunk.hospitalordermanagement.enums.OrderType;
import com.uutrunk.hospitalordermanagement.enums.Status;
import com.uutrunk.hospitalordermanagement.dto.*;
import com.uutrunk.hospitalordermanagement.exception.BeanUtilsException;
import com.uutrunk.hospitalordermanagement.exception.DatabaseException;
import com.uutrunk.hospitalordermanagement.exception.OrderNotExistException;
import com.uutrunk.hospitalordermanagement.exception.TypeUnknownException;
import com.uutrunk.hospitalordermanagement.mapper.*;
import com.uutrunk.hospitalordermanagement.pojo.*;
import com.uutrunk.hospitalordermanagement.service.MedicalOrderService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicalOrderServiceImpl implements MedicalOrderService {

    @Autowired
    private MedicalOrderMainMapper mainMapper;

    @Autowired
    private TemporaryOrderMapper temporaryMapper;

    @Autowired
    private LongTermOrderMapper longMapper;

    @Autowired
    private OrderOperationLogMapper logMapper;

    @Autowired
    private PatientInfoMapper patientInfoMapper;
    @Autowired
    private DoctorInfoMapper doctorInfoMapper;


    private final ChatModel chatModel;

    public MedicalOrderServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(MedicalOrderCreateDTO dto) {

        MedicalOrderMain main = new MedicalOrderMain();
        try {
            main.setOrderId(UUID.randomUUID().toString().replace("-", ""));
            main.setPatientId(dto.getPatientId());
            main.setDoctorId(dto.getDoctorId());
            main.setOrderType(dto.getOrderType());
            main.setContent(dto.getContent());
            main.setDosage(dto.getDosage());
            main.setMedicalUsage(dto.getUsage());
            main.setFrequency(dto.getFrequency());
            main.setSendingTime(LocalDateTime.now());
            main.setOrderStatus(Status.valueOf("待校对"));
            main.setStartingTime(dto.getStartTime());
        }
        catch (RuntimeException e) {
            throw new BeanUtilsException("Bean拷贝失败");
        }

        try {
            System.out.println(main);
            mainMapper.insert(main);
        }
        catch (RuntimeException e) {
            throw new DatabaseException("创建医嘱主表失败");
        }
        String orderId = main.getOrderId();

        // 保存子表信息
        if ("临时".equals(dto.getOrderType())) {
            TemporaryOrder temp = new TemporaryOrder();
            temp.setOrderId(orderId);
            temp.setValidityPeriod(dto.getValidityPeriod());
            temporaryMapper.insert(temp);
        }
        else if("长期".equals(dto.getOrderType())){
            LongTermOrder longTerm = new LongTermOrder();
            longTerm.setOrderId(orderId);
            longTerm.setStopTime(dto.getStopTime());
            longMapper.insert(longTerm);
        }
        else {
            throw new TypeUnknownException("未知的医嘱类型");
        }

        // 记录操作日志
        logOperation(orderId, "创建", dto.getDoctorId());

        return orderId;
    }

    @Override
    public void update(MedicalOrderUpdateDTO dto) {
        // 校验状态是否允许修改
        MedicalOrderMain existing = mainMapper.selectById(dto.getOrderId());
        if (existing == null) {
            throw new OrderNotExistException("医嘱不存在");
        }

        // 更新主表信息
        MedicalOrderMain updateMain = new MedicalOrderMain();
        BeanUtils.copyProperties(dto, updateMain); // 替换BeanCopyUtil
        mainMapper.updateById(updateMain);

        // 记录操作日志
        logOperation(dto.getOrderId(), "修改", dto.getDoctorId());

        return;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(String orderId, Integer doctorId) {
        // 状态校验
        MedicalOrderMain existing = mainMapper.selectById(orderId);
        if (existing == null) {
            throw new OrderNotExistException("医嘱不存在");
        }

        // 更新状态为已作废
        MedicalOrderMain main = new MedicalOrderMain();
        main.setOrderId(orderId);
        main.setOrderStatus(Status.valueOf("已作废"));
        mainMapper.updateById(main);

        // 记录操作日志
        logOperation(orderId, "作废", doctorId);

        return;
    }

    @Override
    public MedicalOrderDTO getById(String orderId) {
        MedicalOrderMain main = mainMapper.selectById(orderId);
        if (main == null) {
            throw new OrderNotExistException("医嘱不存在");
        }

        MedicalOrderDTO dto = new MedicalOrderDTO();
        BeanUtils.copyProperties(main, dto); // 替换BeanCopyUtil

        // 补充子表信息
        if ("临时".equals(main.getOrderType())) {
            TemporaryOrder temp = temporaryMapper.selectById(orderId);
            if (temp == null) {
                throw new OrderNotExistException("临时医嘱子表记录不存在");
            }
            dto.setValidityPeriod(temp.getValidityPeriod());
        } else {
            LongTermOrder longTerm = longMapper.selectById(orderId);
            if (longTerm == null) {
                throw new OrderNotExistException("长期医嘱子表记录不存在");
            }
            dto.setStopTime(longTerm.getStopTime());
        }

        return dto;
    }

    @Override
    public PageResult<MedicalOrderDTO> list(MedicalOrderQueryDTO queryDTO) {
        // 创建分页对象
        Page<MedicalOrderMain> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());

        // 准备参数
        List<Integer> patientIds = getPatientIdsByQuery(queryDTO);

        QueryWrapper<MedicalOrderMain> wrapper = new QueryWrapper<>();
        if (patientIds != null && !patientIds.isEmpty()) {
            wrapper.in("patient_id", patientIds);
        }
        IPage<MedicalOrderMain> mainPage = mainMapper.selectPage(page, wrapper);


        // 转换DTO
        List<MedicalOrderDTO> dtoList = mainPage.getRecords().stream()
                .map(main -> {
                    MedicalOrderDTO dto = new MedicalOrderDTO();
                    dto = dto.fromMainEntity(main);
                    if(main.getOrderType().equals(OrderType.长期)) {
                        LongTermOrder longTerm = longMapper.selectById(main.getOrderId());
                        dto.setStopTime(longTerm.getStopTime());
                    }
                    else if(main.getOrderType().equals(OrderType.临时)) {
                        TemporaryOrder temp = temporaryMapper.selectById(main.getOrderId());
                        dto.setValidityPeriod(temp.getValidityPeriod());
                    }
                    PatientInfo patient = patientInfoMapper.selectById(main.getPatientId());
                    dto.setPatientName(patient.getName());
                    DoctorInfo doctor = doctorInfoMapper.selectById(main.getDoctorId());
                    dto.setDoctorName(doctor.getName());
                    return dto; // 直接使用关联查询的patientName和doctorName
                })
                .collect(Collectors.toList());

        // 构建响应
        PageResult<MedicalOrderDTO> result = new PageResult<>();
        result.setTotal(mainPage.getTotal());
        result.setList(dtoList);
        return result;
    }

    @Override
    public String chat(String message) {
        message = """
                你现在是一位医学知识丰富且临床经验充沛的医学专家
                请你以专业的医学知识回答下列问题:
                """
                + message;
        return this.chatModel.call(message);
    }

    // 辅助方法：获取patient_id列表（可提取为工具方法）
    private List<Integer> getPatientIdsByQuery(MedicalOrderQueryDTO queryDTO) {
        if (queryDTO.getPatientName() == null || queryDTO.getPatientName().trim().isEmpty()) {
            return null;
        }
        QueryWrapper<PatientInfo> wrapper = new QueryWrapper<>();
        wrapper.like("name", queryDTO.getPatientName());
        return patientInfoMapper.selectList(wrapper).stream()
                .map(PatientInfo::getPatientId)
                .collect(Collectors.toList());
    }


    private void logOperation(String orderId, String type, Integer doctorId) {
        OrderOperationLog log = new OrderOperationLog();
        log.setOrderId(orderId);
        log.setOperationType(type);
        log.setOperationTime(LocalDateTime.now());
        log.setDoctorId(doctorId);
        logMapper.insert(log);
    }

}
