package com.learn.kafka.transactions.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<Void, String> kafkaTemplate() {
        KafkaTemplate<Void, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic("out");
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<Void, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.VoidSerializer");
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
        DefaultKafkaProducerFactory<Void, String> producerFactory =
                new DefaultKafkaProducerFactory<>(configProps);
        producerFactory.setTransactionIdPrefix("tx-");
        return producerFactory;
    }

    @Bean
    public KafkaTransactionManager<Void, String> kafkaTransactionManager() {
        return new KafkaTransactionManager<>(producerFactory());
    }
}