package br.com.ottimizza.depara.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.depara.domain.models.DeParaConta;
import feign.Param;

@Repository // @formatter:off
public interface DeParaContaRepository extends PagingAndSortingRepository<DeParaConta, BigInteger>, DeParaContaRepositoryCustom {
	
	 @Query(value = "select count(*) from depara_contas dc where dc.cnpj_contabilidade = :cnpjContabilidade "
	    		+ "and dc.cnpj_empresa = :cnpjEmpresa "
	    		+ "and conta_credito notnull" , nativeQuery = true)
	    long buscaQuantidadeRec(@Param("cnpjContabilidade") String cnpjContabilidade,
	    						@Param("cnpjEmpresa") 		String cnpjEmpresa);
	 
	 @Query(value = "select count(*) from depara_contas dc where dc.cnpj_contabilidade = :cnpjContabilidade "
	    		+ "and dc.cnpj_empresa = :cnpjEmpresa "
	    		+ "and conta_debito notnull" , nativeQuery = true)
	    long buscaQuantidadePag(@Param("cnpjContabilidade") String cnpjContabilidade,
	    						@Param("cnpjEmpresa") 		String cnpjEmpresa);
}