package com.uutrunk.hospitalordermanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uutrunk.hospitalordermanagement.pojo.OrderOperationLog;
import com.uutrunk.hospitalordermanagement.service.OrderOperationLogService;
import com.uutrunk.hospitalordermanagement.mapper.OrderOperationLogMapper;
import org.springframework.stereotype.Service;

/**
* @author uutrunk
* @description 针对表【order_operation_log(医嘱操作记录表)】的数据库操作Service实现
* @createDate 2025-04-03 10:53:01
*/
@Service
public class OrderOperationLogServiceImpl extends ServiceImpl<OrderOperationLogMapper, OrderOperationLog>
    implements OrderOperationLogService{

}




