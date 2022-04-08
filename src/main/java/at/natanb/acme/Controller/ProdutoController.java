package at.natanb.acme.Controller;

import at.natanb.acme.Model.Domain.Fornecedor;
import at.natanb.acme.Model.Domain.Produto;
import at.natanb.acme.Model.Domain.S3Util;
import at.natanb.acme.Model.Service.FornecedorService;
import at.natanb.acme.Model.Service.ProdutoService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public String cadastrarProduto(@RequestBody Produto produto,@PathVariable Integer id)  {
        try{
            produto.setFornecedor(fornecedorService.obterPorId(id));
            produtoService.incluir(produto);
            return "Produto cadastrado com sucesso " + produto.getNome();
        }catch (Exception e){
            return "Falha ao cadastrar Produto" + produto.getNome();
        }
    }
    @PutMapping(value = "/produto/{id}/editar")
    public String editarProduto(@RequestBody Produto produto,@PathVariable Integer id)  {
        try{
            Produto produtoOld = produtoService.obterPorId(id);
            if (produto.getNome() != null){
                produtoOld.setNome(produto.getNome());
            }
            if (produto.getPeso() != null){
                produtoOld.setPeso(produto.getPeso());
            }
            if (produto.getTipo() != null){
                produtoOld.setTipo(produto.getTipo());
            }
            produtoService.incluir(produtoOld);
            return "Produto editado com sucesso " + produtoOld.getNome();
        }catch (Exception e){
            return "Falha ao editar Produto";
        }
    }
    @DeleteMapping(value = "/produto/{id}/excluir")
    public String excluir(@PathVariable Integer id) {
        try{
            produtoService.excluir(id);
            return "Produto excluido com sucesso";
        }catch (Exception e){
            return "Falha ao excluir Fornecedor";
        }
    }
    @PostMapping("/produto/{id}/upload")
    public String produtoIncluirFoto(Model model,@PathVariable Integer id, @RequestParam("file") MultipartFile multipart) throws IOException {
        String fileName = multipart.getOriginalFilename();
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        System.out.println("filename: " + fileName);
        String message = "";
        try {
            Produto produto = produtoService.obterPorId(id);
            S3Util.uploadObjeto(convFile,produto.getId().toString()+produto.getFornecedor().getNome()+".png");
            message = "Arquivo enviado com sucesso!";
        } catch (Exception ex) {
            message = "Erro enviando arquivo: " + ex.getMessage();
        }

        return message;
    }
    @GetMapping(value = "/produto/{id}/foto")
    public String baixarProdutoFoto(@PathVariable Integer id) {
        try{
            Produto produto = produtoService.obterPorId(id);
            System.out.println(produto.getNome());
            S3Util.downloadObjeto(produto.getId().toString()+produto.getFornecedor().getNome()+".png");
            return "Foto do produto " + produto.getNome()+ " baixada com sucesso";
        }catch (Exception e){
            return "Falha ao baixar foto do produto";
        }
    }
    @DeleteMapping(value = "/produto/{id}/foto/excluir")
    public String removerProdutoFoto(@PathVariable Integer id) {
        try{
            Produto produto = produtoService.obterPorId(id);
            System.out.println(produto.getNome());
            S3Util.excluirObjeto(produto.getId().toString()+produto.getFornecedor().getNome()+".png");
            return "Foto do produto " + produto.getNome()+ " excluida com sucesso";
        }catch (Exception e){
            return "Falha ao excluir foto do produto";
        }
    }
}
