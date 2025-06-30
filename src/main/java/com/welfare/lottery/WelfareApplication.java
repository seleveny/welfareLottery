package com.welfare.lottery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "com.welfare.lottery")
@ImportResource({"classpath*:aop-log-conf.xml"})
@MapperScan("com.welfare.lottery.dao")
public class WelfareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WelfareApplication.class, args);
    }

}
