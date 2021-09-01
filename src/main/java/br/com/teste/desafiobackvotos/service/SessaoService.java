/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.service;

import br.com.teste.desafiobackvotos.exception.SessaoException;
import br.com.teste.desafiobackvotos.model.Sessao;
import br.com.teste.desafiobackvotos.repository.SessaoRepository;
import java.time.Instant;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author deivid
 */
@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    public Sessao criar(Sessao sessao) throws SessaoException, NotFoundException {
        sessao.setDataHoraAbertura(Instant.now());
        sessao.setStatus("A");
        sessao.setDuracaoEmMinutos(validarDuracaoEmMinutos(sessao.getDuracaoEmMinutos()));
        sessao.setPauta(this.pautaService.buscarPorCodigo(sessao.getPauta().getCodigo()));
        return this.sessaoRepository.save(sessao);
    }

    public Sessao buscarPorCodigo(Long codigo) throws SessaoException {
        Sessao sessao = this.sessaoRepository.findById(codigo).orElseThrow(() -> new SessaoException("Sessão não encontrada."));
        return sessao;
    }

    private Integer validarDuracaoEmMinutos(Integer duracao) {
        return (duracao == null || duracao <= 0) ? 1 : duracao;
    }
}
