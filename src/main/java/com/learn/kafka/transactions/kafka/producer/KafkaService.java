package com.learn.kafka.transactions.kafka.producer;

import com.learn.kafka.transactions.model.MyPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<Void, Object> kafkaTemplate;

    @Transactional(transactionManager = "kafkaTransactionManager")
    public void sendToKafka(String value) {
        kafkaTemplate.send("in", value);
        kafkaTemplate.send("out", MyPojo.builder()
                .name(value)
                .build());
    }
}
