package com.example;

import com.example.pojo.MySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableBinding(MySource.class)
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

}

