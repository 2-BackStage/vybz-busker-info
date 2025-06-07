package com.vybz.busker_info_service.kafka.consumer;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.infrastructure.BuskerInfoRepository;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import com.vybz.busker_info_service.common.exception.BaseException;
import com.vybz.busker_info_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuskerInfoEventConsumer {

    private final BuskerInfoRepository buskerInfoRepository;

    @KafkaListener(
            topics = "create-busker-auth",
            groupId = "busker-info-group",
            containerFactory = "buskerInfoKafkaListenerContainerFactory"
    )
    public void consumeBuskerInfoEvent(BuskerInfoEvent buskerInfoEvent) {
        log.info("🔥 Kafka 버스커 정보 메시지 수신: {}", buskerInfoEvent);

        boolean exists = buskerInfoRepository.existsByBuskerUuid(buskerInfoEvent.getBuskerUuid());
        if (exists) {
            log.warn("🔥 버스커 정보가 이미 존재합니다. userUuid: {}", buskerInfoEvent.getBuskerUuid());
            throw new BaseException(BaseResponseStatus.DUPLICATE_USER);
        }

        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(buskerInfoEvent.getBuskerUuid())
                .orElse(BuskerInfo.builder()
                        .buskerUuid(buskerInfoEvent.getBuskerUuid())
                        .nickname(buskerInfoEvent.getNickname())
                        .profileImageUrl(buskerInfoEvent.getProfileImageUrl())
                        .build());
        buskerInfoRepository.save(buskerInfo);
        log.info("🔥 버스커 정보 저장 완료: {}", buskerInfo);
    }

}
