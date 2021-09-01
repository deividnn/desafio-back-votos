/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.service;

import br.com.teste.desafiobackvotos.model.Pauta;
import br.com.teste.desafiobackvotos.model.Voto;
import br.com.teste.desafiobackvotos.repository.PautaRepository;
import java.util.List;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author deivid
 */
@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoService votoService;

    @Autowired
    private KafkaSender kafkaSender;

    public Pauta criar(Pauta pauta) {
        return this.pautaRepository.save(pauta);
    }

    public Pauta buscarPorCodigo(Long codigo) throws NotFoundException {
        Pauta pauta = this.pautaRepository.findById(codigo).orElseThrow(() -> new NotFoundException("Pauta não encontrada."));
        return pauta;
    }

    public Pauta obtertResultadoTarefa(Long codigo) throws NotFoundException {
        List<Voto> votos = this.votoService.listarVotos(codigo);

        Pauta pauta = this.pautaRepository.findById(codigo).orElseThrow(() -> new NotFoundException("Pauta não encontrada."));

        Integer votosSim = (int) votos.stream().filter(v -> v.getOpcao().equals("S")).count();

        Integer votosNao = (int) votos.stream().filter(v -> v.getOpcao().equals("N")).count();

        pauta.setVotosNao(votosNao);
        pauta.setVotosSim(votosSim);

        kafkaSender.sendMessage(pauta);

        return pauta;
    }

    public Pauta obtertResultado(Long codigo) throws NotFoundException {
        List<Voto> votos = this.votoService.listarVotos(codigo);

        Pauta pauta = this.pautaRepository.findById(codigo).orElseThrow(() -> new NotFoundException("Pauta não encontrada."));

        Integer votosSim = (int) votos.stream().filter(v -> v.getOpcao().equals("S")).count();

        Integer votosNao = (int) votos.stream().filter(v -> v.getOpcao().equals("N")).count();

        pauta.setVotosNao(votosNao);
        pauta.setVotosSim(votosSim);

        return pauta;
    }

}
