package com.example.pojo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    @Output("erbadagang-output")
    MessageChannel erbadgangOutput();

    @Output("trek-output")
    MessageChannel trekOutput();
}
