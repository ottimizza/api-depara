package br.com.ottimizza.depara.services;

import java.math.BigInteger;
import java.security.Principal;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.domain.dtos.criterias.SearchCriteria;
import br.com.ottimizza.depara.domain.mappers.DeParaContaMapper;
import br.com.ottimizza.depara.domain.models.DeParaConta;
import br.com.ottimizza.depara.repositories.DeParaContaRepository;

@Service // @formatter:off
public class DeParaContaService {

    @Inject
    private DeParaContaRepository deParaContaRepository;

    public Page<DeParaContaDTO> buscarTodos(DeParaContaDTO filtro, SearchCriteria criteria, Principal principal)
            throws Exception {
        return deParaContaRepository.fetchAll(filtro, PageRequest.of(criteria.getPageIndex(), criteria.getPageSize()))
                                    .map(DeParaContaMapper::fromEntity);
    }

    public DeParaContaDTO buscarPorId(BigInteger id, Principal principal) throws Exception { // @formatter:off
        return DeParaContaMapper.fromEntity(deParaContaRepository.findById(id)
                                                                 .orElseThrow(() -> new Exception()));
    }

    public DeParaContaDTO criar(DeParaContaDTO deParaContaDTO, Principal principal) throws Exception { // @formatter:off
        deParaContaDTO.setCnpjContabilidade(deParaContaDTO.getCnpjContabilidade().replaceAll("\\D*", ""));
        deParaContaDTO.setCnpjEmpresa(deParaContaDTO.getCnpjEmpresa().replaceAll("\\D*", ""));

        DeParaConta deParaConta = deParaContaRepository.buscarPorContabilidadeEmpresaEDescricao(
            deParaContaDTO.getCnpjContabilidade(), deParaContaDTO.getCnpjEmpresa(), deParaContaDTO.getDescricao()
        );

        String contaCredito = deParaContaDTO.getContaCredito();
        String contaDebito = deParaContaDTO.getContaDebito();

        System.out.println(deParaConta != null);

        if (deParaConta != null) {

            System.out.println(">> " + contaDebito);
            System.out.println(">> " + contaCredito);
            
            deParaConta = deParaConta.toBuilder()
                .contaCredito(
                    (contaCredito == null || contaCredito.isEmpty()) ? deParaConta.getContaCredito() : contaCredito)
                .contaDebito(
                    (contaDebito == null || contaDebito.isEmpty()) ? deParaConta.getContaDebito() : contaDebito)
                .build();
            
            // deParaConta.setContaCredito((contaCredito == null || contaCredito.isEmpty()) ? deParaConta.getContaCredito() : contaCredito);
            // deParaConta.setContaDebito((contaDebito == null || contaDebito.isEmpty()) ? deParaConta.getContaDebito() : contaDebito);

            System.out.println(">> >> " + deParaConta.getContaDebito());
            System.out.println(">> >> " + deParaConta.getContaCredito());
            
        } else {
            deParaConta = DeParaContaMapper
                .fromDTO(deParaContaDTO)
                    .toBuilder()
                        .username(principal.getName())
                    .build();
        }

        validaDeParaConta(deParaConta);
        return DeParaContaMapper.fromEntity(deParaContaRepository.save(deParaConta));
    }
    
    public DeParaContaDTO buscaPorDescricaoCnpjS(DeParaContaDTO filtro) throws Exception {
    	DeParaConta DeParaConta = null;
    	try {
    		DeParaConta = deParaContaRepository.buscarPorContabilidadeEmpresaEDescricao(filtro.getCnpjContabilidade(), filtro.getCnpjEmpresa(), filtro.getDescricao());
    	}
    	catch(Exception ex) {
    		throw new Exception("DeParaConta não encontrado!");
    	}
		return DeParaContaMapper.fromEntity(DeParaConta);
    }

    private boolean validaDeParaConta(DeParaConta deParaConta) throws IllegalArgumentException {
        if (deParaConta.getCnpjContabilidade() == null || deParaConta.getCnpjContabilidade().equals("")) {
            throw new IllegalArgumentException("Informe o CNPJ da Contabilidade!");
        }
        if (deParaConta.getCnpjEmpresa() == null || deParaConta.getCnpjEmpresa().equals("")) {
            throw new IllegalArgumentException("Informe o CNPJ da Empresa!");
        }
        if (deParaConta.getDescricao() == null || deParaConta.getDescricao().equals("")) {
            throw new IllegalArgumentException("Informe a descrição da conta!");
        }
        if ((deParaConta.getContaCredito() == null || deParaConta.getContaCredito().equals(""))
                && (deParaConta.getContaDebito() == null || deParaConta.getContaDebito().equals(""))) {
            throw new IllegalArgumentException("Informe a conta de crédito ou débito!");
        }
        return true;
    }

}
