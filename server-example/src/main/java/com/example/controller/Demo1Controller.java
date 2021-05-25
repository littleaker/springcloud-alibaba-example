package com.example.controller;

import com.example.pojo.Demo1Message;
import com.example.pojo.MySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/demo01")
@Slf4j
public class Demo1Controller {

    @Autowired
    private MySource mySource;

    @GetMapping("/send")
    public Demo1Message send() {
        Demo1Message message = new Demo1Message();
        message.setId(new Random().nextInt());
        Message<Demo1Message> springMessage = MessageBuilder.withPayload(message).build();
        mySource.erbadgangOutput().send(springMessage);
        return message;
    }

    @GetMapping("/sendTrek")
    public Demo1Message sendTrek() {
        Demo1Message message = new Demo1Message();
        message.setId(new Random().nextInt());
        Message<Demo1Message> springMessage = MessageBuilder.withPayload(message).build();
        mySource.trekOutput().send(springMessage);
        return message;
    }

    @GetMapping("/send_delay")
    public Demo1Message sendDelay() {
        Demo1Message message = new Demo1Message();
        message.setId(new Random().nextInt());
        Message<Demo1Message> springMessage = MessageBuilder.withPayload(message)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "3").build();
        boolean sendResult = mySource.erbadgangOutput().send(springMessage);
        log.info("[sendDelay][发送消息完成，结果 = {}]" ,sendResult);
        return message;
    }

    @GetMapping("/send_tag")
    public Demo1Message sendTag() {
        for (String tag: new String[]{"trek", "specialized", "look"}){
            Demo1Message message = new Demo1Message();
            message.setId(new Random().nextInt());
            Message<Demo1Message> springMessage = MessageBuilder.withPayload(message)
                    .setHeader(MessageConst.PROPERTY_TAGS, tag).build();
            mySource.erbadgangOutput().send(springMessage);
        }
        Demo1Message message = new Demo1Message();
        return message;
    }
}
