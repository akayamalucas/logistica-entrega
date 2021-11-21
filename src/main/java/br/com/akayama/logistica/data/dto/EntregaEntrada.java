package br.com.akayama.logistica.data.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EntregaEntrada {
    private Integer id;
    @Valid
    @NotNull
    private ClienteEntregaEntrada cliente;
    @Valid
    @NotNull
    private Destinatario destinatario;
    @NotNull
    private BigDecimal taxa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteEntregaEntrada getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntregaEntrada cliente) {
        this.cliente = cliente;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }
}
