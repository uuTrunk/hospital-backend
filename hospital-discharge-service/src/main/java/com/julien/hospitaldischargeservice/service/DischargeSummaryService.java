package com.julien.hospitaldischargeservice.service;

import com.julien.hospitaldischargeservice.entity.DischargeSummary;

public interface DischargeSummaryService {

    /**
     * 根据离院记录 ID 获取离院小结
     */
    DischargeSummary getDischargeSummaryByDischargeId(Integer dischargeId);

    /**
     * 提交并保存离院小结
     */
    DischargeSummary saveDischargeSummary(DischargeSummary dischargeSummary);
}
