package com.learn.kafka.transactions.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

    @KafkaListener(topics = "out")
    public void listen(String value) {
        System.out.println("******\n******\n******\n " + value);
    }

}
