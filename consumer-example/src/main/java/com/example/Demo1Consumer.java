package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo1Consumer {

    @StreamListener(MySink.ERBADAGANG_INPUT)
    public void onMessage(@Payload Demo1Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

    @StreamListener(MySink.TREK_INPUT)
    public void onTrekMessage(@Payload Demo1Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
