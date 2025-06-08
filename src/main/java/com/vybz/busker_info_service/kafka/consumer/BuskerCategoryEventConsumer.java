package com.vybz.busker_info_service.kafka.consumer;

import com.vybz.busker_info_service.busker_category.domain.BuskerCategory;
import com.vybz.busker_info_service.busker_category.infrastructure.BuskerCategoryRepository;
import com.vybz.busker_info_service.kafka.event.BuskerCategoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuskerCategoryEventConsumer {

    private final BuskerCategoryRepository buskerCategoryRepository;

    @KafkaListener(
            topics = "create-busker-auth",
            groupId = "busker-category-group",
            containerFactory = "buskerCategoryKafkaListenerContainerFactory"
    )
    public void consumeBuskerCategoryEvent(BuskerCategoryEvent event) {
        log.info("🎯 Kafka 버스커 카테고리 메시지 수신: {}", event);

        for (Long categoryId : event.getCategoryId()) {
            boolean exists = buskerCategoryRepository.existsByBuskerUuidAndCategoryIdAndDeletedFalse(
                    event.getBuskerUuid(), categoryId);

            if (exists) {
                log.info("🚫 이미 존재하는 카테고리: buskerUuid={}, categoryId={}", event.getBuskerUuid(), categoryId);
                continue;
            }

            BuskerCategory category = BuskerCategory.builder()
                    .buskerUuid(event.getBuskerUuid())
                    .categoryId(categoryId)
                    .build();

            buskerCategoryRepository.save(category);
            log.info("✅ 카테고리 저장 완료: {}", category);
        }
    }

}
