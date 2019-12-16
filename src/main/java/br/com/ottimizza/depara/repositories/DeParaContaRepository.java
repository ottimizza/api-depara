package br.com.ottimizza.depara.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.depara.domain.models.DeParaConta;

@Repository // @formatter:off
public interface DeParaContaRepository extends PagingAndSortingRepository<DeParaConta, BigInteger>, DeParaContaRepositoryCustom {

}
