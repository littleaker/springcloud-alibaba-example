package com.example;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableBinding(MySink.class)
public class ConApp {

    @Reference
    private EchoService echoService;

    @GetMapping("/echo")
    public String echo(String message){
        return echoService.echo(message);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConApp.class,args);
    }
}
