package com.learn.kafka.transactions.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<Void, String> kafkaTemplate;

    @Transactional(transactionManager = "kafkaTransactionManager")
    public void sendToKafka(String value) {
        kafkaTemplate.sendDefault(value);
    }
}
