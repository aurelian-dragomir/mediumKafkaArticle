package com.learn.kafka.transactions.config;

import com.learn.kafka.transactions.model.MyPojo;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.DelegatingByTypeSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<Void, Object> kafkaTemplate() {
        KafkaTemplate<Void, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<Void, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class);

        Map<Class<?>, Serializer<?>> serializers = new HashMap<>();
        serializers.put(String.class, new StringSerializer());
        serializers.put(MyPojo.class, new JsonSerializer<MyPojo>());

        DelegatingByTypeSerializer delegatingSerializer =
                new DelegatingByTypeSerializer(serializers);

        DefaultKafkaProducerFactory<Void, Object> producerFactory =
                new DefaultKafkaProducerFactory<>(configProps);

        producerFactory.setValueSerializer(delegatingSerializer);
        producerFactory.setTransactionIdPrefix("tx-");

        return producerFactory;
    }

    @Bean
    public KafkaTransactionManager<Void, Object> kafkaTransactionManager() {
        return new KafkaTransactionManager<>(producerFactory());
    }
}