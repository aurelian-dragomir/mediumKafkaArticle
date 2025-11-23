package com.learn.kafka.transactions.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class KafkaService {

    private final KafkaTemplate<Void, String> kafkaTemplate;
    private final KafkaTransactionManager<Void, String> kafkaTransactionManager;

    @Transactional
    public void send(String... values) {
        for (String value : values) {
            kafkaTemplate.sendDefault(value);
        }
    }
}
