/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.controller;

import br.com.teste.desafiobackvotos.model.Pauta;
import br.com.teste.desafiobackvotos.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author deivid
 */
@RestController
@Api(value = "Pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping("v1/pautas")
    @Deprecated
    @ApiOperation(value = "Cria uma pauta")
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        Pauta pautaCriada = this.pautaService.criar(pauta);
        return ResponseEntity.ok().body(pautaCriada);

    }

    @PostMapping("v2/pautas")
    @ApiOperation(value = "Cria uma pauta.")
    public ResponseEntity<Pauta> criarPautav2(@RequestBody Pauta pauta) {
        Pauta pautaCriada = this.pautaService.criar(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaCriada);

    }

    @GetMapping(value = "v1/pautas/{codigo}/resultado")
    @Deprecated
    @ApiOperation(value = "Obtem resultado de uma pauta")
    public ResponseEntity<Pauta> obterResultadoPauta(@PathVariable Long codigo) throws NotFoundException {
        Pauta pauta = this.pautaService.obtertResultado(codigo);
        return ResponseEntity.ok().body(pauta);
    }

    @GetMapping(value = "v2/pautas/{codigo}/resultado")
    @ApiOperation(value = "Obtem resultado de uma pauta.")
    public ResponseEntity<Pauta> obterResultadoPautav2(@PathVariable Long codigo) throws NotFoundException {
        Pauta pauta = this.pautaService.obtertResultado(codigo);
        return ResponseEntity.ok().body(pauta);
    }

}
