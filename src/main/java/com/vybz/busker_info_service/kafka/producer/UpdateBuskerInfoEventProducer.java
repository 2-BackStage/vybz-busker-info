package com.vybz.busker_info_service.kafka.producer;

import com.vybz.busker_info_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateBuskerInfoEventProducer {

    private final KafkaTemplate<String, BuskerInfoEvent> kafkaTemplate;
    private static final String TOPIC = "update-busker-info";

    public void sendBuskerInfoEvent(BuskerInfoEvent event) {
        log.info("[Kafka] Sending UpdateBuskerInfoEvent to topic '{}': {}", TOPIC, event);
        CompletableFuture<SendResult<String, BuskerInfoEvent>> future =
                kafkaTemplate.send(TOPIC, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send UpdateBuskerInfoEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent UpdateBuskerInfoEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }

}
