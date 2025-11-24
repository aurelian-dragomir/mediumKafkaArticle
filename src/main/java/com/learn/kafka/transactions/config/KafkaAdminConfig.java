package com.learn.kafka.transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {
    @Bean
    public KafkaAdmin.NewTopics createTopicsBean() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("in")
                        .build(),
                TopicBuilder.name("out")
                        .build());
    }
}
