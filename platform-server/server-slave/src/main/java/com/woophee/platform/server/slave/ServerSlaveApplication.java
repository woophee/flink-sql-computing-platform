package com.woophee.platform.server.slave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@PropertySource(value="classpath:server-slave.properties")
public class ServerSlaveApplication {
    public static void main(String[] args){
        SpringApplication.run(ServerSlaveApplication.class, args);
    }
}
