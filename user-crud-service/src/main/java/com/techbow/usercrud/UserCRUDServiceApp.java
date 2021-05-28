package com.techbow.usercrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserCRUDServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCRUDServiceApp.class, args);
    }
}
