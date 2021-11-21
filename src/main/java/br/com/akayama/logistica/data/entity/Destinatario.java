package br.com.akayama.logistica.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class Destinatario {

    @Column(name = "destinatario_nome")
    @NotBlank
    @Size(max = 100)
    private String nome;

    @Column(name = "destinatario_logradouro")
    @NotBlank
    @Size(max = 60)
    private String logradouro;

    @Column(name = "destinatario_numero")
    private String numero;

    @Column(name = "destinatario_complemento")
    @Size(max = 100)
    private String complemento;

    @Column(name = "destinatario_bairro")
    @NotBlank
    @Size(max = 100)
    private String bairro;

    @Column(name = "destinatario_cep")
    @NotBlank
    @Size(max = 15)
    private String cep;

    public Destinatario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
