package com.vybz.busker_info_service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBuskerInfoEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "delete-busker-info";

    public void sendBuskerInfoEvent(String buskerUuid) {
        log.info("[Kafka] Sending DeleteBuskerInfoEvent to topic '{}': {}", TOPIC, buskerUuid);
        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC, buskerUuid);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send DeleteBuskerInfoEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent DeleteBuskerInfoEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }

}
