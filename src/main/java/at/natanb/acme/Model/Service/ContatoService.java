package at.natanb.acme.Model.Service;

import at.natanb.acme.Model.Domain.Contato;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Repository.ContatoRepository;
import at.natanb.acme.Model.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    public Collection<Contato> obterLista(){
        return (Collection<Contato>) contatoRepository.findAll();
    }
    public Collection<Contato> obterListaPorFornecedor(Integer id){
        return (Collection<Contato>) contatoRepository.obterContatosPorFornecedor(id);
    }
    public void incluir(Contato contato) {
        contatoRepository.save(contato);
    }
    public Contato obterPorId(Integer id) {
        return contatoRepository.findById(id).orElse(null);
    }
    public  void excluir(Integer id) {
        contatoRepository.deleteById(id);
    }
}
