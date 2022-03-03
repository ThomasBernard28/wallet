package com.example.accessingdatamariadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.mariadb")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AccessingDataApp {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataApp.class, args);
    }
}
