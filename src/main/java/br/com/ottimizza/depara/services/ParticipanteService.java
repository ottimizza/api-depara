package br.com.ottimizza.depara.services;

import java.math.BigInteger;

import javax.inject.Inject;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ottimizza.depara.domain.dtos.ParticipanteDTO;
import br.com.ottimizza.depara.domain.mappers.ParticipanteMapper;
import br.com.ottimizza.depara.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

	@Inject
	ParticipanteRepository repository;

	public ParticipanteDTO criar(ParticipanteDTO participanteDTO, String authorization) {
		return ParticipanteMapper.fromEntity(repository.save(ParticipanteMapper.fromDTO(participanteDTO)));
	}

	public Object atualizar(BigInteger id, ParticipanteDTO participanteDTO, String authorization) {
		throw new NotYetImplementedException();
	}

	public ParticipanteDTO buscarPorId(BigInteger id, String authorization) {
		return ParticipanteMapper.fromEntity(repository.findById(id).orElse(null));
	}

	public Boolean excluir(BigInteger id, String authorization) { //implementar retorno correto (json)
		if(repository.findById(id).orElse(null) != null) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	public Page<ParticipanteDTO> buscarTodos(ParticipanteDTO filter,int pageIndex, int pageSize, String authorization) {
		return repository.findByCnpjContabilidadeAndCnpjParticipante(filter.getCnpjContabilidade(), 
																	 filter.getCnpjParticipante(), 
																	 PageRequest.of(pageIndex, pageSize)).map(ParticipanteMapper::fromEntity);
	}
	
}
