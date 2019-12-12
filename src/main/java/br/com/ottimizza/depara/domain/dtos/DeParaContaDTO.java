package br.com.ottimizza.depara.domain.dtos;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DeParaContaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger id;

    private String cnpjContabilidade;

    private String cnpjEmpresa;

    private String descricao;

    private String contaDebito;

    private String contaCredito;

    private String username;

    private LocalDate dataCriacao;

    private LocalDate dataAtualizacao;

}
