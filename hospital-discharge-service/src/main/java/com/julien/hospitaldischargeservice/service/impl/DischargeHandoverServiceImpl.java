package com.julien.hospitaldischargeservice.service.impl;

import com.julien.hospitaldischargeservice.entity.DischargeHandover;
import com.julien.hospitaldischargeservice.entity.DischargeMain;
import com.julien.hospitaldischargeservice.repository.DischargeHandoverRepository;
import com.julien.hospitaldischargeservice.repository.DischargeMainRepository;
import com.julien.hospitaldischargeservice.service.DischargeHandoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DischargeHandoverServiceImpl implements DischargeHandoverService {

    private final DischargeHandoverRepository dischargeHandoverRepository;
    private final DischargeMainRepository dischargeMainRepository; // 注入 DischargeMainRepository

    @Override
    public boolean submitDischargeHandover(Integer dischargeId, List<DischargeHandover> handoverItems) {
        try {
            // 查找对应的 DischargeMain 实体
            DischargeMain dischargeMain = dischargeMainRepository.findById(dischargeId).orElse(null);

            if (dischargeMain == null) {
                // 若找不到对应的离院记录，返回 false
                return false;
            }

            // 处理交接项目
            for (DischargeHandover handoverItem : handoverItems) {
                handoverItem.setDischargeMain(dischargeMain); // 直接设置对应的 DischargeMain 实体
                dischargeHandoverRepository.save(handoverItem); // 保存交接项目
            }

            return true;
        } catch (Exception e) {
            // 异常处理，记录日志等
            return false;
        }
    }
}
