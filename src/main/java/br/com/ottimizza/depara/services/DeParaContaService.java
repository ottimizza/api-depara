package br.com.ottimizza.depara.services;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.domain.mappers.DeParaContaMapper;
import br.com.ottimizza.depara.domain.models.DeParaConta;
import br.com.ottimizza.depara.repositories.DeParaContaRepository;

@Service
public class DeParaContaService {

    @Inject
    private DeParaContaRepository deParaContaRepository;

    public List<DeParaContaDTO> buscarTodos(Principal principal) throws Exception {
        throw new NotYetImplementedException();
    }

    public DeParaContaDTO criar(DeParaContaDTO deParaContaDTO, Principal principal) throws Exception {
        DeParaConta deParaConta = DeParaContaMapper.fromDTO(deParaContaDTO);
        validaDeParaConta(deParaConta);
        return DeParaContaMapper.fromEntity(deParaContaRepository.save(deParaConta));
    }

    private boolean validaDeParaConta(DeParaConta deParaConta) throws IllegalArgumentException {
        if (deParaConta.getCnpjContabilidade() == null || deParaConta.getCnpjContabilidade().equals("")) {
            throw new IllegalArgumentException("Informe o CNPJ da Contabilidade!");
        }
        return true;
    }

}
