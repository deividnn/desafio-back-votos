package br.com.teste.desafiobackvotos.repository;

import br.com.teste.desafiobackvotos.model.Sessao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    List<Sessao> findByStatus(String status);

}
