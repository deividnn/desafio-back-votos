/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.controller;

import br.com.teste.desafiobackvotos.exception.CPFInvalidoException;
import br.com.teste.desafiobackvotos.exception.SessaoException;
import br.com.teste.desafiobackvotos.exception.VotoException;
import br.com.teste.desafiobackvotos.model.Voto;
import br.com.teste.desafiobackvotos.service.VotoService;
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
@Api(value = "Voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("v1/votos")
    @Deprecated
    @ApiOperation(value = "Cria um voto")
    public ResponseEntity<Voto> criarSessao(@RequestBody Voto voto) throws NotFoundException, SessaoException, CPFInvalidoException, VotoException {
        Voto votoCriado = this.votoService.criar(voto);
        return ResponseEntity.ok().body(votoCriado);

    }

    @PostMapping("v2/votos")
    @ApiOperation(value = "Cria um voto.")
    public ResponseEntity<Voto> criarSessaov2(@RequestBody Voto voto) throws NotFoundException, SessaoException, CPFInvalidoException, VotoException {
        Voto votoCriado = this.votoService.criar(voto);
        return ResponseEntity.status(HttpStatus.CREATED).body(votoCriado);

    }

}
