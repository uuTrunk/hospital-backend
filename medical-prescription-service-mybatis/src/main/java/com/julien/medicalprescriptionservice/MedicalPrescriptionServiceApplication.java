package com.julien.medicalprescriptionservice;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.julien.medicalprescriptionservice.mapper")  // Scan for MyBatis mappers
public class MedicalPrescriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalPrescriptionServiceApplication.class, args);
    }
}
