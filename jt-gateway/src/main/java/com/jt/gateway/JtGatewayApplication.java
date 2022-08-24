package com.jt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * JtGatewayApplication
 *
 * @Author: jt
 * @Date: 2022/8/23 9:06
 */
//参数解释：exclude---不需要自动装配数据库，scanBasePackages---springBoot 的自动装配 扫描的spring启动类同级的包及其子包,但是由于不是统一个项目导致扫描不到bean!
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class },scanBasePackages = "com.jt")
public class JtGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(JtGatewayApplication.class,args);
        System.out.println("(♥◠‿◠)ﾉﾞ ====jt网关启动成功====");
    }
}
