package com.tassel.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: zsy
 * @date: 2020/12/13 21:35
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class/*不加载数据源默认自动配置*/)
@ComponentScan(basePackages = {"com.tassel"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
