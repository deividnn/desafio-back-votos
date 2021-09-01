/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.controller;

import br.com.teste.desafiobackvotos.model.Pauta;
import br.com.teste.desafiobackvotos.model.Sessao;
import br.com.teste.desafiobackvotos.repository.PautaRepository;
import br.com.teste.desafiobackvotos.service.PautaService;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import junit.framework.Assert;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author deivid
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessaControllerTest {
    
    @Value(value = "${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    @Order(1)
    void criarSessaoTest() throws URISyntaxException {

        String baseUrl = "http://localhost:" + randomServerPort +contextPath+ "v2/pautas";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        Pauta pauta = new Pauta();
        pauta.setNome("Pauta" + Instant.now().getEpochSecond());

        HttpEntity<Pauta> request = new HttpEntity<>(pauta, headers);
        ResponseEntity<Pauta> result = this.restTemplate.postForEntity(uri, request, Pauta.class);

        Assertions.assertEquals(201, result.getStatusCodeValue());

        baseUrl = "http://localhost:" + randomServerPort +contextPath+  "v2/sessoes";
        uri = new URI(baseUrl);

        Sessao sessao = new Sessao();
        sessao.setDataHoraAbertura(Instant.now());
        sessao.setDuracaoEmMinutos(180);
        sessao.setPauta(result.getBody());

        HttpEntity<Sessao> request2 = new HttpEntity<>(sessao, headers);
        ResponseEntity<Sessao> result2 = this.restTemplate.postForEntity(uri, request2, Sessao.class);

        Assertions.assertEquals(201, result2.getStatusCodeValue());

    }

}
