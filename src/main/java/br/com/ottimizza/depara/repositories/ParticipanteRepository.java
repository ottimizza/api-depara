package br.com.ottimizza.depara.repositories;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.depara.domain.models.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, BigInteger> {

	  @Query(value = "SELECT p.* FROM participantes p WHERE p.cnpj_contabilidade = :cnpjContabilidade AND p.cnpj_participante = :cnpjParticipante ", 
			 nativeQuery = true)
	  Page<Participante> findByCnpjContabilidadeAndCnpjParticipante(@Param("cnpjContabilidade") String cnpjContabilidade, 
			  														@Param("cnpjParticipante") String cnpjParticipante, 
			  						 								Pageable pageable);
}
