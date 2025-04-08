package com.julien.hospitaldischargeservice.service;

import com.julien.hospitaldischargeservice.entity.DischargeHandover;

import java.util.List;

public interface DischargeHandoverService {

    /**
     * 提交离院交接操作
     */
    boolean submitDischargeHandover(Integer dischargeId, List<DischargeHandover> handoverItems);
}
