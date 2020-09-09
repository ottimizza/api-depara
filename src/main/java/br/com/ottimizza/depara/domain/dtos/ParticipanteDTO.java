package br.com.ottimizza.depara.domain.dtos;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ParticipanteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	
	private String cnpjContabilidade;
	
	private String codigoParicipante;
	
	private String cnpjParticipante;
	
	private String descricaoContinua;
	
	private String descricao;


}
