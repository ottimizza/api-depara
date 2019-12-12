package br.com.ottimizza.depara.repositories;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.depara.domain.models.DeParaConta;

@Repository
public interface DeParaContaRepository extends PagingAndSortingRepository<DeParaConta, BigInteger> {

}
