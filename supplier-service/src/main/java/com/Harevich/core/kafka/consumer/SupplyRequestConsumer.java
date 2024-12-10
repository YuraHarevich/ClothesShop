package com.Harevich.core.kafka.consumer;

import com.Harevich.core.kafka.consumer.SupplyRequest;
import com.Harevich.core.kafka.producer.ClothesSupply;
import com.Harevich.core.mapper.SupplyClothesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SupplyRequestConsumer {
    @Value("${spring.kafka.topic.supply}")
    private String supplyTopic;
    @Value("${spring.kafka.topic.clothes}")
    private String clothesTopic;
    private final KafkaTemplate<String, ClothesSupply> kafkaTemplate;
    @KafkaListener(topics = "supply-topic")
    public void consumeSupplyRequests(SupplyRequest supplyRequest)throws MessagingException {
        log.info("Consuming the message from topic {}",supplyTopic);
        sendClothes(SupplyClothesMapper.toClothes(supplyRequest));
    }

    public void sendClothes(ClothesSupply clothesSupply){
        log.info("Sending clothes");
        Message<ClothesSupply> message = MessageBuilder
                .withPayload(clothesSupply)
                .setHeader(KafkaHeaders.TOPIC,clothesTopic)
                .build();
        kafkaTemplate.send(message);
    }
}
