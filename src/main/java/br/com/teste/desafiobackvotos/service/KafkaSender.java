/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.service;

/**
 *
 * @author deivid
 */
import br.com.teste.desafiobackvotos.model.Pauta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaSender {

    private final KafkaTemplate<String, Pauta> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, Pauta> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Pauta pauta) {
        log.info("Mensagem enviada: {}", pauta.toString());
        kafkaTemplate.send("desafio-voto", pauta);
    }
}
