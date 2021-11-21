package br.com.akayama.logistica.data.entity;

import br.com.akayama.logistica.data.ValidationGroups;
import br.com.akayama.logistica.exception.RequisicaoInvalidaException;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @ConvertGroup(from = Default.class, to = ValidationGroups.EntregaClienteId.class) // a partir da entrega, apenas o id do cliente é validado
    @NotNull
    @Valid
    private Cliente cliente;

    @Embedded
    @Valid
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_entrega")
    private StatusEntrega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "entrega_id")
    private List<Movimento> movimentos = new ArrayList<>();

    public Entrega() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
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

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public OffsetDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(OffsetDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public Movimento adicionarMovimento(String descricao) {
        Movimento movimento = new Movimento();
        movimento.setDataRegistro(OffsetDateTime.now());
        movimento.setDescricao(descricao);
        movimento.setEntrega(this);

        this.movimentos.add(movimento);

        return movimento;
    }

    public void finalizar() {
        if(!StatusEntrega.PENDENTE.equals(getStatus())) {
            throw new RequisicaoInvalidaException("Entrega não pode ser finalizada");
        }

        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return id.equals(entrega.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
