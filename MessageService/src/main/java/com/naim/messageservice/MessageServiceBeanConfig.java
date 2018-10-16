package com.naim.messageservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.naim.messageservice")
public class MessageServiceBeanConfig {
    @LoadBalanced
    @Bean(name="restTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
