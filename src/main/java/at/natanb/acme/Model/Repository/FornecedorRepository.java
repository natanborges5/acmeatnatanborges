package at.natanb.acme.Model.Repository;

import at.natanb.acme.Model.Domain.Cotacao;
import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FornecedorRepository extends CrudRepository<Fornecedor, Integer> {
}
