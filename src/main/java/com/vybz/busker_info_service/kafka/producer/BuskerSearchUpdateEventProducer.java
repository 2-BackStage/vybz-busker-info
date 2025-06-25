package com.vybz.busker_info_service.kafka.producer;


import com.vybz.busker_info_service.kafka.event.BuskerSearchUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuskerSearchUpdateEventProducer {

    private final KafkaTemplate<String, BuskerSearchUpdateEvent> kafkaTemplate;
    public static final String TOPIC_NAME = "create-busker-search";

    public void send(BuskerSearchUpdateEvent event){
        log.info("[Kafka] sending BuskerSearchUpdateEvent to topic '{}': {}", TOPIC_NAME, event);

        CompletableFuture<SendResult<String,BuskerSearchUpdateEvent>> future =
                kafkaTemplate.send(TOPIC_NAME, event.getBuskerUuid(), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send BuskerSearchUpdateEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent BuskerSearchUpdateEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }
}
