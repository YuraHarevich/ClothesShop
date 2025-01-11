package com.Harevich.core.kafka;


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
public class OrderNotificationConsumer {
    @Value("${spring.kafka.topic.notification}")
    private String notificationTopic;

    private final KafkaTemplate<String, OrderNotification> kafkaTemplate;
    @KafkaListener(topics = "notification-topic")
    public void consumeSupplyRequests(OrderNotification orderNotification)throws MessagingException {
        log.info("Consuming the message from topic {} for order {}",notificationTopic,orderNotification.orderId());
    }

}
