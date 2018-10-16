package com.naim.messageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MessageServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApp.class,args);
    }

}

