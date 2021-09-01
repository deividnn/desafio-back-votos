/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.service;

import br.com.teste.desafiobackvotos.exception.CPFInvalidoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author deivid
 */
@Slf4j
@Service
public class ConsultaCpfService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean consutarCpf(String cpf) throws CPFInvalidoException {
        try {

            String url = "https://user-info.herokuapp.com/users/" + cpf;
            ResponseEntity<String> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    String.class);
            log.info("retorno consulta cpf:{}", response.getBody());
            return response.getBody().equals("{\"status\":\"ABLE_TO_VOTE\"}");
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CPFInvalidoException("Cpf " + cpf + " invalido ");
            }
            throw new CPFInvalidoException("Erro ao consultar cpf");
        }

    }

}
