package br.com.teste.desafiobackvotos.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sessao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false)
    private Instant dataHoraAbertura;

    @Column(nullable = false)
    private Integer duracaoEmMinutos;

    @ManyToOne(optional = false)
    private Pauta pauta;

    @Column(length = 1, nullable = false)
    private String status;
}
