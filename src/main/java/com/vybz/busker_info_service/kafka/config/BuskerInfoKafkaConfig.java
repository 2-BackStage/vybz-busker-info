package com.vybz.busker_info_service.kafka.config;

import com.vybz.busker_info_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@RequiredArgsConstructor
public class BuskerInfoKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, BuskerInfoEvent> buskerInfoEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(BuskerInfoEvent.class, false))
        );
    }

    @Bean(name = "buskerInfoKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, BuskerInfoEvent> buskerInfoKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BuskerInfoEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(buskerInfoEventConsumerFactory());
        return factory;
    }

}
