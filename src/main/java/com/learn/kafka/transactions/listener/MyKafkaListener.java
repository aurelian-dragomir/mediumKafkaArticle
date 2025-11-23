package com.learn.kafka.transactions.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyKafkaListener {

    private final KafkaTemplate<Void, String> kafkaTemplate;

    @KafkaListener(topics = "in")
    public void listen(String value) {
        log.info("Received value {} in topic in", value);
        String upperCase = value.toUpperCase();
        kafkaTemplate.sendDefault(upperCase);
    }

}
