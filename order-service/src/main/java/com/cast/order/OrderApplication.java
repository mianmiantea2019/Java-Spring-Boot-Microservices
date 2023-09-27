package com.cast.order;

import com.cast.demo.clients.UserClient;
import com.cast.demo.config.DefaultFeignConfiguration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.cast.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = UserClient.class, defaultConfiguration = DefaultFeignConfiguration.class) // order-service的@EnableFeignClients注解是在cn.itcast.order包下，不在同一个包

public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }
}