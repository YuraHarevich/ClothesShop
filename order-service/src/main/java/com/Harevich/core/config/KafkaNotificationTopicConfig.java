package com.Harevich.core.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaNotificationTopicConfig {
    @Value("${spring.kafka.topic.supply}")
    private String topic;
    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder
                .name(topic)
                .build();
    }
}
