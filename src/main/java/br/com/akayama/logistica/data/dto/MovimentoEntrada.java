package br.com.akayama.logistica.data.dto;

import javax.validation.constraints.NotBlank;

public class MovimentoEntrada {

    @NotBlank
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
