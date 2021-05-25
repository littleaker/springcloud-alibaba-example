package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String ERBADAGANG_INPUT = "erbadagang-input";
    String TREK_INPUT = "trek-input";

    @Input(ERBADAGANG_INPUT)
    SubscribableChannel demo1Input();

    @Input(TREK_INPUT)
    SubscribableChannel trekInput();
}
