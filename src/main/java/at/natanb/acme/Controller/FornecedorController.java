package at.natanb.acme.Controller;

import at.natanb.acme.Model.Domain.Cotacao;
import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping(value = "/fornecedores")
    public Collection<Fornecedor> fornecedores() {
        return fornecedorService.obterLista();
    }

    @GetMapping(value = "/fornecedor/{id}")
    public Fornecedor fornecedoresById(@PathVariable Integer id) {
        return fornecedorService.obterPorId(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/fornecedor/cadastro" ,consumes={"application/json"})
    public String cadastrarFornecedor(@RequestBody Fornecedor fornecedor){
        try{
            fornecedorService.incluir(fornecedor);
            return "Fornecedor cadastrado com sucesso " + fornecedor.getNome();
        }catch (Exception e){
            System.out.println("Falha ao cadastrar fornecedor " + fornecedor.getNome());
            return "Falha ao cadastrar Fornecedor" + fornecedor.getNome();
        }
    }
    @PutMapping(value = "/fornecedor/{id}/editar")
    public String editarFornecedor(@RequestBody Fornecedor fornecedor, @PathVariable Integer id)  {
        try{
            Fornecedor fornecedorOld = fornecedorService.obterPorId(id);
            if (fornecedor.getNome() != null){
                fornecedorOld.setNome(fornecedor.getNome());
            }
            if (fornecedor.getRamo() != null){
                fornecedorOld.setRamo(fornecedor.getRamo());
            }
            fornecedorService.incluir(fornecedorOld);
            return "Fornecedor editado com sucesso " + fornecedorOld.getNome();
        }catch (Exception e){
            return "Falha ao editar fornecedor";
        }
    }
    @DeleteMapping(value = "/fornecedor/{id}/excluir")
    public String excluir(@PathVariable Integer id) {
        try{
            fornecedorService.excluir(id);
            return "Fornecedor excluido com sucesso";
        }catch (Exception e){
            System.out.println("Falha ao excluir fornecedor ");
            return "Falha ao excluir Fornecedor";
        }
    }
}
