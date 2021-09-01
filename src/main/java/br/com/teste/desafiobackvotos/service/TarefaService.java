/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.service;

import br.com.teste.desafiobackvotos.model.Sessao;
import br.com.teste.desafiobackvotos.repository.SessaoRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author deivi
 */
@Slf4j
@Service
public class TarefaService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void scheduleFixedRateWithInitialDelayTask() throws NotFoundException {

        List<Sessao> sessoes = this.sessaoRepository.findByStatus("A");
        for (Sessao sessao : sessoes) {
            if (validarDuracaoDaSessao(sessao)) {
                log.info("atualizando sessao finalizada {}", sessao);
                sessao.setStatus("F");
                this.sessaoRepository.save(sessao);
                this.pautaService.obtertResultadoTarefa(sessao.getPauta().getCodigo());
            }
        }
    }

    private boolean validarDuracaoDaSessao(Sessao sessao) {
        Instant agora = Instant.now();
        Duration duracao = Duration.between(agora, sessao.getDataHoraAbertura());
        if (Math.abs(duracao.toMinutes()) > sessao.getDuracaoEmMinutos()) {
            return true;
        }
        return false;
    }
}
