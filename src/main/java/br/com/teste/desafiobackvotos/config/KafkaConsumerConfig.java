/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.config;

/**
 *
 * @author deivid
 */
import br.com.teste.desafiobackvotos.model.Pauta;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.url}")
    private String kafkaUrl;

    public ConsumerFactory<String, Pauta> pautaConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "br.com.teste.desafiobackvotos");
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(Pauta.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Pauta> pautaKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Pauta> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pautaConsumerFactory());
        return factory;
    }
}
