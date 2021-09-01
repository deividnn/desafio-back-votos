/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.service;

import br.com.teste.desafiobackvotos.exception.CPFInvalidoException;
import br.com.teste.desafiobackvotos.exception.SessaoException;
import br.com.teste.desafiobackvotos.exception.VotoException;
import br.com.teste.desafiobackvotos.model.Sessao;
import br.com.teste.desafiobackvotos.model.Voto;
import br.com.teste.desafiobackvotos.repository.VotoRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author deivid
 */
@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private ConsultaCpfService consultaCpfService;

    public Voto criar(Voto voto) throws NotFoundException, VotoException, CPFInvalidoException, SessaoException {
        validarOpcao(voto.getOpcao());
        voto.setSessao(this.sessaoService.buscarPorCodigo(voto.getSessao().getCodigo()));
        validarDuracaoDaSessao(voto.getSessao());
        verificarSeCpfJaVotou(voto.getSessao().getCodigo(), voto.getCpf());
        if (!consultaCpfService.consutarCpf(voto.getCpf())) {
            throw new VotoException("Associado não pode votar");
        }
        return this.votoRepository.save(voto);
    }

    public List<Voto> listarVotos(Long codigoPauta) {
        return this.votoRepository.findBySessaoPautaCodigo(codigoPauta);
    }

    private void verificarSeCpfJaVotou(Long codigoSessao, String cpf) throws VotoException {
        Optional<Voto> voto = this.votoRepository.findVotoBySessaoCodigoAndCpf(codigoSessao, cpf);
        if (voto.isPresent()) {
            throw new VotoException("Associado ja votou nessa sessão!");
        }

    }

    private void validarDuracaoDaSessao(Sessao sessao) throws VotoException {
        Instant agora = Instant.now();
        Duration duracao = Duration.between(agora, sessao.getDataHoraAbertura());
        if (Math.abs(duracao.toMinutes()) > sessao.getDuracaoEmMinutos()) {
            throw new VotoException("Sessão ja finalizada!");
        }
    }

    private void validarOpcao(String opcao) throws VotoException {
        if (!opcao.equals("S") && !opcao.equals("N")) {
            throw new VotoException("Opcão de voto invalida!");
        }
    }
}
