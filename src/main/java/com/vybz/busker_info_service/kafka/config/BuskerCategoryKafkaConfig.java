package com.vybz.busker_info_service.kafka.config;

import com.vybz.busker_info_service.kafka.event.BuskerCategoryEvent;
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
public class BuskerCategoryKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, BuskerCategoryEvent> buskerCategoryEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(BuskerCategoryEvent.class, false))
        );
    }

    @Bean(name = "buskerCategoryKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, BuskerCategoryEvent> buskerCategoryKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BuskerCategoryEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(buskerCategoryEventConsumerFactory());
        return factory;
    }

}
