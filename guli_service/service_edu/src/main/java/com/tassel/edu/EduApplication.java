package com.tassel.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: zsy
 * @date: 2020/12/5 9:41
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tassel"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
