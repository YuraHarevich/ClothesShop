package com.Harevich.core.kafka;

import com.Harevich.core.service.ClothesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClothesConsumer {
    @Value("${spring.kafka.topic.clothes}")
    private String clothesTopic;

    private final ClothesService service;

    @KafkaListener(topics = "clothes-topic")
    public void consumePaymentSuccessNotifications(ClothesSupply clothesSupply) throws MessagingException {
        log.info("Consuming the message from topic {}",clothesTopic);
        service.supplyClothes(clothesSupply);
    }
}
