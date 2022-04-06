package at.natanb.acme.Model.Repository;

import at.natanb.acme.Model.Domain.Cotacao;
import at.natanb.acme.Model.Domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CotacaoRepositoy extends CrudRepository<Cotacao, Integer> {
//    @Query("from Cotacao  u where u.fornecedor = :id")
//    Collection<Cotacao> findAllByFornecedor(Integer id);
    @Query("from Cotacao  u where u.produto = :id")
    Collection<Cotacao> findAllByProduto(Integer id);
}
