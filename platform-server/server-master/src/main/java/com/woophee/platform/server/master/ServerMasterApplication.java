package com.woophee.platform.server.master;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.woophee.platform.server.master.dao.mapper")
@PropertySource(value="classpath:server-master.properties")
public class ServerMasterApplication {
    public static void main(String[] args){
        SpringApplication.run(ServerMasterApplication.class, args);
    }
}
