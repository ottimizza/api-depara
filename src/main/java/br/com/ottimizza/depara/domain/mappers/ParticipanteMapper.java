package br.com.ottimizza.depara.domain.mappers;

import br.com.ottimizza.depara.domain.dtos.ParticipanteDTO;
import br.com.ottimizza.depara.domain.models.Participante;

public class ParticipanteMapper {

	public static Participante fromDTO(ParticipanteDTO o) {
		return Participante.builder()
							.id(o.getId())
							.cnpjContabilidade(o.getCnpjContabilidade())
							.codigoParticipante(o.getCodigoParticipante())
							.cnpjParticipante(o.getCnpjParticipante())
							.descricaoContinua(o.getDescricaoContinua())
							.descricao(o.getDescricao())
							.build();
	}

	public static ParticipanteDTO fromEntity(Participante o) {
		return ParticipanteDTO.builder()
							.id(o.getId())
							.cnpjContabilidade(o.getCnpjContabilidade())
							.codigoParticipante(o.getCodigoParticipante())
							.cnpjParticipante(o.getCnpjParticipante())
							.descricaoContinua(o.getDescricaoContinua())
							.descricao(o.getDescricao())
							.build();
	}
}
