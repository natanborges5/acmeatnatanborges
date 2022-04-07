package at.natanb.acme.Model.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TProdutos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String peso;
    private String tipo;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    @JsonManagedReference
    @OneToMany(mappedBy = "produto")
    private Collection<Cotacao> cotacaos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public Collection<Cotacao> getCotacaos() {
        return cotacaos;
    }

    public void setCotacaos(Collection<Cotacao> cotacaos) {
        this.cotacaos = cotacaos;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
