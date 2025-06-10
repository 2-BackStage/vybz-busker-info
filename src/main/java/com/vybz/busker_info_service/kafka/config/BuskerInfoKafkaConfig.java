package com.vybz.busker_info_service.kafka.config;

import com.vybz.busker_info_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@RequiredArgsConstructor
public class BuskerInfoKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ProducerFactory<String, BuskerInfoEvent> buskerInfoProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonKafkaConfig.commonProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, BuskerInfoEvent> buskerInfoKafkaTemplate() {
        return new KafkaTemplate<>(buskerInfoProducerFactory());
    }

    @Bean
    public ProducerFactory<String, String> stringBuskerInfoProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonKafkaConfig.commonProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> stringBuskerInfoKafkaTemplate() {
        return new KafkaTemplate<>(stringBuskerInfoProducerFactory());
    }


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
