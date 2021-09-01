/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.controller;

import br.com.teste.desafiobackvotos.exception.SessaoException;
import br.com.teste.desafiobackvotos.model.Sessao;
import br.com.teste.desafiobackvotos.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author deivid
 */
@RestController
@Api(value = "Sessão")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("v1/sessoes")
    @ApiOperation(value = "Cria uma sessao")
    public ResponseEntity<Sessao> criarSessao(@RequestBody Sessao sessao) throws NotFoundException, SessaoException {
        Sessao sessaoCriada = this.sessaoService.criar(sessao);
        return ResponseEntity.ok().body(sessaoCriada);

    }

    @PostMapping("v2/sessoes")
    @ApiOperation(value = "Cria uma sessao.")
    public ResponseEntity<Sessao> criarSessaov2(@RequestBody Sessao sessao) throws NotFoundException, SessaoException {
        Sessao sessaoCriada = this.sessaoService.criar(sessao);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoCriada);

    }

}
