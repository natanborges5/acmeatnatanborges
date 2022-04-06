package at.natanb.acme.Model.Repository;

import at.natanb.acme.Model.Domain.Contato;
import at.natanb.acme.Model.Domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface ContatoRepository extends CrudRepository<Contato, Integer> {
    @Query("from Contato  u where u.fornecedor = :id")
    Collection<Contato> obterContatosPorFornecedor(Integer id);
}
