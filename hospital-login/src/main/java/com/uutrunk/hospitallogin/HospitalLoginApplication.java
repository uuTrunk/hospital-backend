package com.uutrunk.hospitallogin;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.uutrunk.hospitallogin.mapper")
public class HospitalLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalLoginApplication.class, args);
    }

}
