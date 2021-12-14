package com.butter.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@EnableEurekaClient
@EnableRabbit
@MapperScan("com.butter.admin.mapper")
@ServletComponentScan(basePackages = "com.butter.admin")
@SpringBootApplication
public class WebDatastructureAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDatastructureAdminApplication.class, args);
    }

}
