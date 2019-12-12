package br.com.ottimizza.depara.domain.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "depara_contas")
@Builder(toBuilder = true)
public class DeParaConta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "depara_contas_sequence", sequenceName = "depara_contas_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depara_contas_sequence")
    private BigInteger id;

    @Column(nullable = false)
    private String cnpjContabilidade;

    @Column(nullable = false)
    private String cnpjEmpresa;

    @Column(nullable = false)
    private String descricao;

    private String contaDebito;

    private String contaCredito;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dataCriacao;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dataAtualizacao;

    @PreUpdate
    @PrePersist
    public void atualizaTimestamps() {
        if (dataCriacao == null) {
            dataCriacao = LocalDate.now();
        }
        dataAtualizacao = LocalDate.now();
    }

}
