package com.vybz.busker_info_service.kafka.config;

import com.vybz.busker_info_service.kafka.event.BuskerSearchUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class BuskerSearchUpdateKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ProducerFactory<String, BuskerSearchUpdateEvent> buskerSearchUpdateEventProducerFactory(){
        return new DefaultKafkaProducerFactory<>(commonKafkaConfig.commonProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, BuskerSearchUpdateEvent> buskerSearchUpdateEventKafkaTemplate() {
        return new KafkaTemplate<>(buskerSearchUpdateEventProducerFactory());
    }


}
