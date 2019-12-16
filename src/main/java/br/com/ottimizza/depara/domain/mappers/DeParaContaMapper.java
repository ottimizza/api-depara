package br.com.ottimizza.depara.domain.mappers;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.domain.models.DeParaConta;

public class DeParaContaMapper {

    public static DeParaConta fromDTO(DeParaContaDTO o) {
        return DeParaConta.builder()
                .id(o.getId())
                .descricao(o.getDescricao())
                .contaDebito(o.getContaDebito())
                .contaCredito(o.getContaCredito())
                .cnpjContabilidade(o.getCnpjContabilidade())
                .cnpjEmpresa(o.getCnpjEmpresa())
                .username(o.getUsername())
                .dataCriacao(o.getDataCriacao())
                .dataAtualizacao(o.getDataAtualizacao())
            .build();
    }

    public static DeParaContaDTO fromEntity(DeParaConta o) {
        return DeParaContaDTO.builder()
                .id(o.getId())
                .descricao(o.getDescricao())
                .contaDebito(o.getContaDebito())
                .contaCredito(o.getContaCredito())
                .cnpjContabilidade(o.getCnpjContabilidade())
                .cnpjEmpresa(o.getCnpjEmpresa())
                .username(o.getUsername())
                .dataCriacao(o.getDataCriacao())
                .dataAtualizacao(o.getDataAtualizacao())
            .build();
    }

}
