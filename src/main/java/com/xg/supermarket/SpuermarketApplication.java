package com.xg.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.xg.supermarket.mapper")
public class SpuermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpuermarketApplication.class, args);
    }

}
