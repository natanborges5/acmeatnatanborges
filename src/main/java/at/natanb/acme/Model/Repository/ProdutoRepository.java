package at.natanb.acme.Model.Repository;

import at.natanb.acme.Model.Domain.Cotacao;
import at.natanb.acme.Model.Domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    @Query("from Produto  u where u.fornecedor = :id")
    Collection<Produto> obterProdutosPorFornecedor(Integer id);
}
