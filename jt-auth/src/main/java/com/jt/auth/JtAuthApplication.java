package com.jt.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * JtAuthApplication
 * 认证授权中心
 * @Author: jt
 * @Date: 2022/8/22 16:07
 */
@EnableFeignClients(basePackages ="com.jt")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JtAuthApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(JtAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功 ");
    }
}
