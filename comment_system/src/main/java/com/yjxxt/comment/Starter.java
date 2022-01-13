package com.yjxxt.comment;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yjxxt.comment.mapper")
public class Starter {
    private static Logger logger = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);
    }
}
