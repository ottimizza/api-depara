package br.com.ottimizza.depara.domain.dtos;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import br.com.ottimizza.depara.domain.models.DeParaConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeParaContaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger id;

    private String cnpjContabilidade;

    private String cnpjEmpresa;

    private String descricao;

    private String contaDebito;

    private String contaCredito;

    private String username;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;


    public DeParaConta patch(DeParaConta dePara) {

        if(descricao != null && !descricao.equals(""))
            dePara.setDescricao(descricao);

        if(contaDebito != null && !contaDebito.equals(""))
            dePara.setContaDebito(contaDebito);

        if(contaCredito != null && !contaCredito.equals(""))
            dePara.setContaCredito(contaCredito);

        if(username != null && !username.equals(""))
            dePara.setUsername(username);
            
        return dePara;
    }

}
