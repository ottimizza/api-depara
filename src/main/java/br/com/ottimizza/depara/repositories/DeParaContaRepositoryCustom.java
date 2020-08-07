package br.com.ottimizza.depara.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.domain.models.DeParaConta;

public interface DeParaContaRepositoryCustom { // DeParaContaRepositoryImpl

    Page<DeParaConta> fetchAll(DeParaContaDTO filter, Pageable pageable);

    DeParaConta buscarPorContabilidadeEmpresaEDescricao(String cnpjContabilidade, String cnpjEmpresa, String descricao);
    
}