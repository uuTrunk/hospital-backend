package com.uutrunk.hospitalordermanagement;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.uutrunk.hospitalordermanagement.mapper")
public class HospitalOrdermanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalOrdermanagementApplication.class, args);
    }

}
