package br.com.teste.desafiobackvotos.model;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "voto")
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne(optional = false)
    private Sessao sessao;

    @Column(length = 1, nullable = false)
    private String opcao;

    @Column(length = 14, nullable = false)
    private String cpf;

}
