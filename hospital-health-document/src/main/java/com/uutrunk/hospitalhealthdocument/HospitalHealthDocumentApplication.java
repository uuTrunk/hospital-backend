package com.uutrunk.hospitalhealthdocument;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.uutrunk.hospitalhealthdocument.mapper")
public class HospitalHealthDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalHealthDocumentApplication.class, args);
    }

}
