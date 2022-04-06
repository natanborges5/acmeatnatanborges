package at.natanb.acme.Model.Service;

import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Repository.FornecedorRepository;
import at.natanb.acme.Model.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Collection<Produto> obterLista(){
        return (Collection<Produto>) produtoRepository.findAll();
    }
    public Collection<Produto> obterListaPorFornecedor(Integer id){
        return (Collection<Produto>) produtoRepository.obterProdutosPorFornecedor(id);
    }
    public void incluir(Produto produto) {
        produtoRepository.save(produto);
    }
    public Produto obterPorId(Integer id) {
        return produtoRepository.findById(id).orElse(null);
    }
    public  void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }
}
