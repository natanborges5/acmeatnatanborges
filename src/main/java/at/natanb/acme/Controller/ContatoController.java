package at.natanb.acme.Controller;

import at.natanb.acme.Model.Domain.Contato;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Service.ContatoService;
import at.natanb.acme.Model.Service.FornecedorService;
import at.natanb.acme.Model.Service.ProdutoService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController

public class ContatoController {
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ContatoService contatoService;

    @GetMapping(value = "/contatos")
    public Collection<Contato> contatos() {
        return contatoService.obterLista();
    }
    @GetMapping(value = "/fornecedor/{id}/contatos")
    public Collection<Contato> produtosPorFornecedor(@PathVariable Integer id) {
        return contatoService.obterListaPorFornecedor(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/fornecedor/{id}/contato/cadastro")
    public String cadastrarContato(@RequestBody Contato contato,@PathVariable Integer id){
        try{
            contato.setFornecedor(fornecedorService.obterPorId(id));
            contatoService.incluir(contato);
            return "Contato cadastrado com sucesso " + contato.getEmail();
        }catch (Exception e){
            return "Falha ao cadastrar Contato" + contato.getEmail();
        }
    }
    @GetMapping(value = "/contato/{id}/excluir")
    public String excluir(@PathVariable Integer id) {
        try{
            contatoService.excluir(id);
            return "Contato excluido com sucesso";
        }catch (Exception e){
            return "Falha ao excluir Contato";
        }
    }
}
