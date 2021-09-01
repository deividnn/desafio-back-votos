package br.com.teste.desafiobackvotos.repository;

import br.com.teste.desafiobackvotos.model.Voto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository< Voto, Long> {

    Optional<Voto> findVotoBySessaoCodigoAndCpf(Long codigSessao, String cpf);

    List<Voto> findBySessaoPautaCodigo(Long codigoPauta);
}
