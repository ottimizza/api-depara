package br.com.ottimizza.depara.domain.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "depara_contas", indexes = {
    @Index(name = "idx_depara_contas_contab_empresa_descricao", columnList = "cnpj_contabilidade,cnpj_empresa,descricao")
})
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeParaConta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "depara_contas_sequence", sequenceName = "depara_contas_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depara_contas_sequence")
    private BigInteger id;

    @Column(name = "cnpj_contabilidade", nullable = false)
    private String cnpjContabilidade;

    @Column(name = "cnpj_empresa", nullable = false)
    private String cnpjEmpresa;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "conta_debito")
    private String contaDebito;

    @Column(name = "conta_credito")
    private String contaCredito;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @PreUpdate
    @PrePersist
    public void atualizaTimestamps() {
        // Strings
        descricao = descricao.toUpperCase();
        cnpjContabilidade = cnpjContabilidade.replaceAll("\\D*", "");
        cnpjEmpresa = cnpjEmpresa.replaceAll("\\D*", "");

        // Timestamps
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
        dataAtualizacao = LocalDateTime.now();
    }

}
