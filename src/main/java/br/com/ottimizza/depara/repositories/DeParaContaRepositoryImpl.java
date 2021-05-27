package br.com.ottimizza.depara.repositories;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.stereotype.Repository;

import br.com.ottimizza.depara.domain.dtos.DeParaContaDTO;
import br.com.ottimizza.depara.domain.models.DeParaConta;
import br.com.ottimizza.depara.domain.models.QDeParaConta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Sort
import com.querydsl.core.types.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;

@Repository
public class DeParaContaRepositoryImpl implements DeParaContaRepositoryCustom {

    private QDeParaConta qDeParaConta = QDeParaConta.deParaConta;

    private long totalElements;

    @PersistenceContext
    EntityManager em;

    @Override
    public Page<DeParaConta> fetchAll(DeParaContaDTO filter, Pageable pageable) {
        JPAQuery<DeParaConta> query = new JPAQuery<DeParaConta>(em).from(qDeParaConta);
        totalElements = filter(query, filter);
        sort(query, pageable, DeParaConta.class, "deParaConta");
        paginate(query, pageable);
        return new PageImpl<DeParaConta>(query.fetch(), pageable, totalElements);
    }

    @Override
    public DeParaConta buscarPorContabilidadeEmpresaEDescricao(String cnpjContabilidade, String cnpjEmpresa, String descricao) {
        JPAQuery<DeParaConta> query = new JPAQuery<DeParaConta>(em).from(qDeParaConta);

        query.where(qDeParaConta.cnpjContabilidade.like(cnpjContabilidade.toUpperCase()));
        query.where(qDeParaConta.cnpjEmpresa.like(cnpjEmpresa.toUpperCase()));
        query.where(qDeParaConta.descricao.like(descricao.toUpperCase()));

        return query.fetchFirst();
    }
    
    //
    //
    //
    private <T> long filter(JPAQuery<T> query, DeParaContaDTO filter) {
        if (filter.getId() != null)
            query.where(qDeParaConta.id.eq(filter.getId()));

        if (filter.getDescricao() != null && !filter.getDescricao().isEmpty())
            query.where(qDeParaConta.descricao.eq(filter.getDescricao().toUpperCase()));

        if (filter.getCnpjContabilidade() != null && !filter.getCnpjContabilidade().isEmpty())
            query.where(qDeParaConta.cnpjContabilidade.like(filter.getCnpjContabilidade()));

        if (filter.getCnpjEmpresa() != null && !filter.getCnpjEmpresa().isEmpty())
            query.where(qDeParaConta.cnpjEmpresa.like(filter.getCnpjEmpresa()));

        if (filter.getContaDebito() != null && !filter.getContaDebito().isEmpty())
            query.where(qDeParaConta.contaDebito.like(filter.getContaDebito()));

        if (filter.getContaCredito() != null && !filter.getContaCredito().isEmpty())
            query.where(qDeParaConta.contaCredito.like(filter.getContaCredito()));

        return query.fetchCount();
    }

    private <T> JPAQuery<T> paginate(JPAQuery<T> query, Pageable pageable) {
        query.limit(pageable.getPageSize());
        query.offset(pageable.getPageSize() * pageable.getPageNumber());
        return query;
    }

    private <T> JPAQuery<T> sort(JPAQuery<T> query, Pageable pageable, Class<T> clazz, String value) {
        PathBuilder<T> entityPath = new PathBuilder<T>(clazz, value);
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Object> propertyPath = entityPath.get(order.getProperty());
            query.orderBy(new OrderSpecifier(Order.valueOf(order.getDirection().name()), propertyPath));
        }
        return query;
    }

}
