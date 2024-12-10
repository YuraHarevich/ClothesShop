package com.Harevich.core.kafka.producer;

import com.Harevich.core.order.dto.SupplyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String,SupplyRequest> kafkaTemplate;
    @Value("${spring.kafka.topic.supply}")
    private String topic;
    public void sendOrderConfirmation(SupplyRequest supplyRequest){
        log.info("Sending supply request");
        Message<SupplyRequest> message = MessageBuilder
                .withPayload(supplyRequest)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .build();
        kafkaTemplate.send(message);
    }
}