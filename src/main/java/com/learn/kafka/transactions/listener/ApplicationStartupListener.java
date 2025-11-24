package com.learn.kafka.transactions.listener;

import com.learn.kafka.transactions.kafka.producer.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ApplicationStartupListener {

    private final MyService myService;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
//        myService.sendAndCommitDatabaseTxFirst("one");

        myService.sendAndCommitKafkaTxFirst("one");
    }
}