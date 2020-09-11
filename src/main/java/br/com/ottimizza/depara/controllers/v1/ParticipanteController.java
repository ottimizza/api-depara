package br.com.ottimizza.depara.controllers.v1;

import java.math.BigInteger;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.depara.domain.dtos.ParticipanteDTO;
import br.com.ottimizza.depara.domain.responses.GenericPageableResponse;
import br.com.ottimizza.depara.services.ParticipanteService;

@RestController
@RequestMapping("/api/v1/participantes")
public class ParticipanteController {
	
	@Inject
	ParticipanteService service;
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody ParticipanteDTO participanteDTO, @RequestHeader("Authorization") String authorization) throws Exception {
        return ResponseEntity.ok(service.criar(participanteDTO, authorization));
    }
	
	@PatchMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") BigInteger id, @RequestBody ParticipanteDTO participanteDTO, @RequestHeader("Authorization") String authorization)
            throws Exception {
        return ResponseEntity.ok(service.atualizar(id, participanteDTO, authorization));
    }
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") BigInteger id, @RequestHeader("Authorization") String authorization) throws Exception {
		return (service.buscarPorId(id, authorization) != null) ? ResponseEntity.ok(service.buscarPorId(id, authorization)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") BigInteger id, @RequestHeader("Authorization") String authorization) throws Exception {
		return ResponseEntity.ok(service.excluir(id, authorization).toString());
	}
	
	@GetMapping
	public ResponseEntity<?> buscarTodos(@ModelAttribute ParticipanteDTO filter, 
															@RequestParam(name = "page_index", defaultValue = "0") int pageIndex,
													        @RequestParam(name = "page_size", defaultValue = "10") int pageSize, 
													        @RequestHeader("Authorization") String authorization) throws Exception {
		return ResponseEntity.ok(new GenericPageableResponse<ParticipanteDTO>(service.buscarTodos(filter, pageIndex, pageSize, authorization)));
	}
	
}

