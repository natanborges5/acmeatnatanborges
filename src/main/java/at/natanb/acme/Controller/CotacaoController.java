package at.natanb.acme.Controller;

import at.natanb.acme.Model.Domain.Cotacao;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Service.CotacaoService;
import at.natanb.acme.Model.Service.FornecedorService;
import at.natanb.acme.Model.Service.ProdutoService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Collection;
@RestController
public class CotacaoController {
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping(value = "/cotacoes")
    public Collection<Cotacao> cotacoes() {
        return cotacaoService.obterLista();
    }

//    @GetMapping(value = "/fornecedor/{id}/cotacoes")
//    public Collection<Cotacao> cotacoesPorFornecedor(@PathVariable Integer id) {
//        return cotacaoService.obterListaPorFornecedor(id);
//    }
    @GetMapping(value = "/produto/{idproduto}/cotacoes")
    public Collection<Cotacao> cotacoesPorProduto(@PathVariable Integer id) {
        return cotacaoService.obterListaPorProduto(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/fornecedor/{id}/produto/{idproduto}/cotacoes/cadastro")
    public String cadastrarCotacao(@RequestBody Cotacao cotacao, @PathVariable Integer id, @PathVariable Integer idproduto){
        try{
            if(cotacao.getData() == null){
                cotacao.setData(DateTime.now().toString());
            }
            Produto produto = produtoService.obterPorId(id);
//            cotacao.setFornecedor(fornecedorService.obterPorId(id));
            cotacao.setProduto(produtoService.obterPorId(idproduto));
            cotacaoService.incluir(cotacao);
            return "Cotacao cadastrado com sucesso "+ produto.getNome() + cotacao.getValor() + cotacao.getData();
        }catch (Exception e){
            return "Falha ao cadastrar Cotacao" + cotacao.getValor();
        }
    }
    @GetMapping(value = "/cotacao/{id}/excluir")
    public String excluir(@PathVariable Integer id) {
        try{
            cotacaoService.excluir(id);
            return "Cotacao excluido com sucesso";
        }catch (Exception e){
            return "Falha ao excluir Cotacao";
        }
    }
}
