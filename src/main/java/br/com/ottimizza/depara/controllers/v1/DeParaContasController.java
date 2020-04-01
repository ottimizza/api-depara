package br.com.ottimizza.depara.controllers.v1;

import java.math.BigInteger;
import java.security.Principal;

import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.domain.dtos.criterias.SearchCriteria;
import br.com.ottimizza.depara.domain.responses.GenericPageableResponse;
import br.com.ottimizza.depara.services.DeParaContaService;

@RestController // @formatter:off
@RequestMapping("/api/v1/depara_contas")
public class DeParaContasController {

    @Inject
    private DeParaContaService deParaContaService;

    @GetMapping
    public HttpEntity<?> buscarTodos(@ModelAttribute DeParaContaDTO filtro, 
                                     @ModelAttribute SearchCriteria criteria, 
                                     Principal principal) throws Exception {
        return ResponseEntity.ok(new GenericPageableResponse<DeParaContaDTO>(
            deParaContaService.buscarTodos(filtro, criteria, principal)));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> buscarPorId(@PathVariable BigInteger id, Principal principal) throws Exception {
        return ResponseEntity.ok(deParaContaService.buscarPorId(id, principal));
    }

    @PostMapping
    public HttpEntity<?> criar(@RequestBody DeParaContaDTO deParaContaDTO, Principal principal) throws Exception {
        return ResponseEntity.ok(deParaContaService.criar(deParaContaDTO, principal));
    }

    @PutMapping
    public HttpEntity<?> atualizar(Principal principal) throws Exception {
        throw new NotYetImplementedException();
    }
    
    @GetMapping("/dePara")
    public HttpEntity<?> buscaPorCnpjsDescricao(@Valid DeParaContaDTO filtro, Principal principal) throws Exception {
    	return ResponseEntity.ok(deParaContaService.buscaPorDescricaoCnpjS(filtro));
    }urn ResponseEntity.ok(deParaContaService.buscarPorContabilidadeEmpresaEDescricao(filtro.getCnpjContabilidade(), filtro.getCnpjEmpresa(), filtro.getDescricao()));                                                         
    }
}
