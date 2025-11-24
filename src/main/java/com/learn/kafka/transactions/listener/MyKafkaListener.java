package com.learn.kafka.transactions.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyKafkaListener {

    @KafkaListener(topics = "in")
    public void listen(String value) {
        log.info("Received value {}", value);
    }
}
