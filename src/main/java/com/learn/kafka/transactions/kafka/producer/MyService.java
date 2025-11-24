package com.learn.kafka.transactions.kafka.producer;

import com.learn.kafka.transactions.entity.MyEntity;
import com.learn.kafka.transactions.entity.MyEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class MyService {

    private final KafkaTemplate<Void, String> kafkaTemplate;
    private final MyEntityRepository myEntityRepository;
    private final KafkaService kafkaService;

    @Transactional(transactionManager = "transactionManager")
    public void sendAndCommitDatabaseTxFirst(String value) {
        myEntityRepository.save(MyEntity.builder()
                .name(value)
                .build());
        kafkaTemplate.sendDefault(value);
    }

    @Transactional(transactionManager = "transactionManager")
    public void sendAndCommitKafkaTxFirst(String value) {
        myEntityRepository.save(MyEntity.builder()
                .name(value)
                .build());
        kafkaService.sendToKafka(value);
    }
}
