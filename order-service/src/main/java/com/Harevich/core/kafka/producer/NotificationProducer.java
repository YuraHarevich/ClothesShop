package com.Harevich.core.kafka.producer;

import com.Harevich.core.kafka.OrderNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, OrderNotification> kafkaTemplate;

    public void sendNotificationConfirmation(OrderNotification orderNotification){
        log.info("Sending order notification");
        Message<OrderNotification> message = MessageBuilder
                .withPayload(orderNotification)
                .setHeader(KafkaHeaders.TOPIC,"notification-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
