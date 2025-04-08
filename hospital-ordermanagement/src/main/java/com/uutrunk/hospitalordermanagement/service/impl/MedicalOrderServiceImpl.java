package com.uutrunk.hospitalordermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderCreateDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderQueryDTO;
import com.uutrunk.hospitalordermanagement.dto.MedicalOrderUpdateDTO;
import com.uutrunk.hospitalordermanagement.mapper.LongTermOrderMapper;
import com.uutrunk.hospitalordermanagement.mapper.MedicalOrderMainMapper;
import com.uutrunk.hospitalordermanagement.mapper.OrderOperationLogMapper;
import com.uutrunk.hospitalordermanagement.mapper.TemporaryOrderMapper;
import com.uutrunk.hospitalordermanagement.pojo.*;
import com.uutrunk.hospitalordermanagement.service.MedicalOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalOrderDTO create(MedicalOrderCreateDTO dto) {
        // 生成唯一订单ID
        String orderId = UUID.randomUUID().toString().replace("-", "");
        MedicalOrderMain main = new MedicalOrderMain();
        BeanUtils.copyProperties(dto, main); // 替换BeanCopyUtil
        main.setOrderId(orderId);
        main.setSendTime(LocalDateTime.now());
        mainMapper.insert(main);

        // 保存子表信息
        if ("临时".equals(dto.getOrderType())) {
            TemporaryOrder temp = new TemporaryOrder();
            temp.setOrderId(orderId);
            temp.setValidityPeriod(dto.getValidityPeriod());
            temporaryMapper.insert(temp);
        } else {
            LongTermOrder longTerm = new LongTermOrder();
            longTerm.setOrderId(orderId);
            longTerm.setStopTime(dto.getStopTime());
            longMapper.insert(longTerm);
        }

        // 记录操作日志
        logOperation(orderId, "创建", dto.getDoctorId());

        MedicalOrderDTO result = new MedicalOrderDTO();
        BeanUtils.copyProperties(main, result); // 替换BeanCopyUtil
        return result;
    }

    @Override
    public boolean update(MedicalOrderUpdateDTO dto) {
        // 校验状态是否允许修改
        MedicalOrderMain existing = mainMapper.selectById(dto.getOrderId());
        if (existing == null) {
            throw new RuntimeException("医嘱不存在");
        }
        if (existing.getStatus().equals("已执行") || existing.getStatus().equals("已作废")) {
            throw new RuntimeException("该状态不允许修改");
        }

        // 更新主表信息
        MedicalOrderMain updateMain = new MedicalOrderMain();
        BeanUtils.copyProperties(dto, updateMain); // 替换BeanCopyUtil
        updateMain.setOrderId(dto.getOrderId());
        mainMapper.updateById(updateMain);

        // 记录操作日志
        logOperation(dto.getOrderId(), "修改", dto.getDoctorId());

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean voidOrder(String orderId, Integer operatorId) {
        // 状态校验
        MedicalOrderMain existing = mainMapper.selectById(orderId);
        if (existing == null) {
            throw new RuntimeException("医嘱不存在");
        }
        if (existing.getStatus().equals("已执行") || existing.getStatus().equals("已作废")) {
            throw new RuntimeException("该状态不允许作废");
        }

        // 更新状态为已作废
        MedicalOrderMain main = new MedicalOrderMain();
        main.setOrderId(orderId);
        main.setStatus("已作废");
        mainMapper.updateById(main);

        // 记录操作日志
        logOperation(orderId, "作废", operatorId);

        return true;
    }

    @Override
    public MedicalOrderDTO getById(String orderId) {
        MedicalOrderMain main = mainMapper.selectById(orderId);
        if (main == null) {
            throw new RuntimeException("医嘱不存在");
        }

        MedicalOrderDTO dto = new MedicalOrderDTO();
        BeanUtils.copyProperties(main, dto); // 替换BeanCopyUtil

        // 补充子表信息
        if ("临时".equals(main.getOrderType())) {
            TemporaryOrder temp = temporaryMapper.selectById(orderId);
            if (temp == null) {
                throw new RuntimeException("临时医嘱子表记录不存在");
            }
            dto.setValidityPeriod(temp.getValidityPeriod());
        } else {
            LongTermOrder longTerm = longMapper.selectById(orderId);
            if (longTerm == null) {
                throw new RuntimeException("长期医嘱子表记录不存在");
            }
            dto.setStopTime(longTerm.getStopTime());
        }

        return dto;
    }

    @Override
    public IPage<MedicalOrderDTO> list(MedicalOrderQueryDTO queryDTO) {
        // 创建分页对象
        Page<MedicalOrderMain> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        
        // 构建查询条件
        QueryWrapper<MedicalOrderMain> wrapper = new QueryWrapper<>();
        if (queryDTO.getOrderType() != null) {
            wrapper.eq("order_type", queryDTO.getOrderType());
        }
        if (queryDTO.getStatus() != null) {
            wrapper.eq("status", queryDTO.getStatus());
        }


        // 执行分页查询
        IPage<MedicalOrderMain> mainPage = mainMapper.selectPage(page, wrapper);
        
        // 转换为DTO
        IPage<MedicalOrderDTO> dtoPage = new Page<>();
        dtoPage.setPages(mainPage.getPages());
        dtoPage.setTotal(mainPage.getTotal());
        dtoPage.setRecords(mainPage.getRecords().stream()
            .map(main -> {
                MedicalOrderDTO dto = new MedicalOrderDTO();
                BeanUtils.copyProperties(main, dto);
                return dto;
            }).collect(Collectors.toList()));
        
        return dtoPage;
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
