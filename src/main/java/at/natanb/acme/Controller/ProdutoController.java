package at.natanb.acme.Controller;

import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Service.FornecedorService;
import at.natanb.acme.Model.Service.ProdutoService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping(value = "/produtos")
    public Collection<Produto> produtos() {
        return produtoService.obterLista();
    }
    @GetMapping(value = "/fornecedor/{id}/produtos")
    public Collection<Produto> produtosPorFornecedor(@PathVariable Integer id) {
        return produtoService.obterListaPorFornecedor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/fornecedor/{id}/produto/cadastro")
    public String cadastrarFornecedor(@RequestBody Produto produto,@PathVariable Integer id){
        try{
            produto.setFornecedor(fornecedorService.obterPorId(id));
            produtoService.incluir(produto);
            return "Produto cadastrado com sucesso " + produto.getNome();
        }catch (Exception e){
            return "Falha ao cadastrar Produto" + produto.getNome();
        }
    }
    @GetMapping(value = "/produto/{id}/excluir")
    public String excluir(@PathVariable Integer id) {
        try{
            produtoService.excluir(id);
            return "Produto excluido com sucesso";
        }catch (Exception e){
            return "Falha ao excluir Fornecedor";
        }
    }
}
