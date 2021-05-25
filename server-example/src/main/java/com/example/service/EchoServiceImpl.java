package com.example.service;

import com.example.EchoService;

@org.apache.dubbo.config.annotation.Service
public class EchoServiceImpl implements EchoService {

    public String echo(String message) {
        return "[echo] Hello," + message;
    }
}
