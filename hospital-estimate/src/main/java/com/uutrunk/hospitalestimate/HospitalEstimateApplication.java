package com.uutrunk.hospitalestimate;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.uutrunk.hospitalestimate.mapper")
public class HospitalEstimateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalEstimateApplication.class, args);
    }

}
