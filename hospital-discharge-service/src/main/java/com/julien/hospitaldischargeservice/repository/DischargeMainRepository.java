package com.julien.hospitaldischargeservice.repository;

import com.julien.hospitaldischargeservice.entity.DischargeMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface DischargeMainRepository extends JpaRepository<DischargeMain, Integer> {

    /**
     * 通过多个筛选条件查询离院记录，支持分页
     */
    @Query("""
        SELECT d FROM DischargeMain d
        LEFT JOIN d.patientInfo p
        WHERE (:startDate IS NULL OR d.dischargeDate >= :startDate)
        AND (:endDate IS NULL OR d.dischargeDate <= :endDate)
        AND (:nameOrCode IS NULL OR p.name LIKE %:nameOrCode% OR CONCAT(p.patientId, '') LIKE %:nameOrCode%)
    """)
    Page<DischargeMain> findDischarges(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("nameOrCode") String nameOrCode,
            Pageable pageable
    );
}
