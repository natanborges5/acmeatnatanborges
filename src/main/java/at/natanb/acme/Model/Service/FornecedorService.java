package at.natanb.acme.Model.Service;

import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Collection<Fornecedor> obterLista(){
        return (Collection<Fornecedor>) fornecedorRepository.findAll();
    }
    public void incluir(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }
    public Fornecedor obterPorId(Integer id) {
        return fornecedorRepository.findById(id).orElse(null);
    }
    public  void excluir(Integer id) {
        fornecedorRepository.deleteById(id);
    }
}
