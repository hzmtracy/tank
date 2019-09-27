package com.tank.discoveryeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DiscoveryEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEurekaClientApplication.class, args);
    }

}
