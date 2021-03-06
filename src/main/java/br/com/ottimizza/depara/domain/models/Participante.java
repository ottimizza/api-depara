package br.com.ottimizza.depara.domain.models;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "participantes", indexes = {
		@Index(name = "idx_participantes_participante_contabilidade", columnList = "cnpj_participante,cnpj_contabilidade", unique = true)})
public class Participante implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "participante_sequence", sequenceName = "participante_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participante_sequence")
	private BigInteger id;
	
	@Column(name = "cnpj_contabilidade", length = 14, nullable = false)
	private String cnpjContabilidade;
	
	@Column(name = "codigo_participante", length = 20)
	private String 	codigoParticipante;
	
	@Column(name = "cnpj_participante", length = 20, nullable = false)
	private String cnpjParticipante;
	
	@Column(name = "descricao_continua", columnDefinition = "varchar(255) default ''")
	private String descricaoContinua;
	
	@Column(name = "descricao", columnDefinition = "varchar(255) default ''")
	private String descricao;
	
}
