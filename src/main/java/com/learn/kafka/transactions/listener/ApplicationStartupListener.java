package com.learn.kafka.transactions.listener;

import com.learn.kafka.transactions.kafka.producer.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ApplicationStartupListener {

    private final KafkaService kafkaService;
    private final KafkaTransactionManager<Void, String> kafkaTransactionManager;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        kafkaService.send("one", "two");
    }
}