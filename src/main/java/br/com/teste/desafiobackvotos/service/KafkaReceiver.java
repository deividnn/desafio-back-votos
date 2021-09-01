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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaReceiver {

    @KafkaListener(topics = "desafio-voto",
            groupId = "br.com.teste.desafiobackvotos",
            containerFactory = "pautaKafkaListenerContainerFactory")
    public void listen(Pauta pauta) {
        log.info("Mensagem recebida: {}", pauta.toString());
    }
}
