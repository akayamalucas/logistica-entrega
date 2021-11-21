package br.com.akayama.logistica.data.dto;

import javax.validation.constraints.NotNull;

public class ClienteEntregaEntrada {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
