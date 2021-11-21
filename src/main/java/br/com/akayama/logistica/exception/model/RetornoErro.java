package br.com.akayama.logistica.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetornoErro {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;

    private List<Atributo> atributos;

    public RetornoErro(Integer status, LocalDateTime dataHora, String titulo) {
        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
    }

    public RetornoErro(Integer status, LocalDateTime dataHora, String titulo, List<Atributo> atributos) {
        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
        this.atributos = atributos;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


}
