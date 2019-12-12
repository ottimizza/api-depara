package br.com.ottimizza.depara.controllers.v1;

import java.security.Principal;

import javax.inject.Inject;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.services.DeParaContaService;

@RestController
@RequestMapping("/api/v1/depara_contas")
public class DeParaContasController {

    @Inject
    private DeParaContaService deParaContaService;

    @GetMapping
    public HttpEntity<?> buscarTodos(Principal principal) throws Exception {
        return ResponseEntity.ok(deParaContaService.buscarTodos(principal));
    }

    @PostMapping
    public HttpEntity<?> criar(@RequestBody DeParaContaDTO deParaContaDTO, Principal principal) throws Exception {
        return ResponseEntity.ok(deParaContaService.criar(deParaContaDTO, principal));
    }

    @PutMapping
    public HttpEntity<?> atualizar(Principal principal) throws Exception {
        throw new NotYetImplementedException();
    }

}
