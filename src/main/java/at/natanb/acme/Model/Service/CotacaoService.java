package at.natanb.acme.Model.Service;

import at.natanb.acme.Model.Domain.Cotacao;
import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Repository.CotacaoRepositoy;
import at.natanb.acme.Model.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CotacaoService {
    @Autowired
    private CotacaoRepositoy cotacaoRepositoy;

    public Collection<Cotacao> obterLista(){
        return (Collection<Cotacao>) cotacaoRepositoy.findAll();
    }
    public void incluir(Cotacao cotacao) {
        cotacaoRepositoy.save(cotacao);
    }
    public Cotacao obterPorId(Integer id) {
        return cotacaoRepositoy.findById(id).orElse(null);
    }
    public  void excluir(Integer id) {
        cotacaoRepositoy.deleteById(id);
    }

//    public Collection<Cotacao> obterListaPorFornecedor(Integer id) {
//      return (Collection<Cotacao>) cotacaoRepositoy.findAllByFornecedor(id);
//    }
    public Collection<Cotacao> obterListaPorProduto(Integer id) {
        return (Collection<Cotacao>) cotacaoRepositoy.findAllByProduto(id);
    }
}
