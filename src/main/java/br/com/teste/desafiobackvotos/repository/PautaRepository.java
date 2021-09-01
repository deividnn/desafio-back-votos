package br.com.teste.desafiobackvotos.repository;

import br.com.teste.desafiobackvotos.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository< Pauta, Long> {
}
