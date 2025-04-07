package com.julien.medicalprescriptionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.julien.medicalprescriptionservice.mapper")
public class MedicalPrescriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalPrescriptionServiceApplication.class, args);
    }

}
