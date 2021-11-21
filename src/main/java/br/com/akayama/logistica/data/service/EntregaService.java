package br.com.akayama.logistica.data.service;

import br.com.akayama.logistica.data.entity.Cliente;
import br.com.akayama.logistica.data.entity.Entrega;
import br.com.akayama.logistica.data.entity.StatusEntrega;
import br.com.akayama.logistica.data.repository.EntregaRepository;
import br.com.akayama.logistica.exception.RequisicaoInvalidaException;
import br.com.akayama.logistica.exception.RecursoNaoEncontradoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class EntregaService extends AbstractCrudService<Entrega, Integer> {

    private EntregaRepository entregaRepository;
    private ClienteService clienteService;

    public EntregaService(EntregaRepository entregaRepository, ClienteService clienteService) {
        this.entregaRepository = entregaRepository;
        this.clienteService = clienteService;
    }

    @Override
    protected EntregaRepository getRepository() {
        return this.entregaRepository;
    }

    public Entrega findEntregaById(Integer entregaId) {
        return this.entregaRepository.findById(entregaId).orElseThrow(() -> new RecursoNaoEncontradoException(String.format("Entrega com id %s não encontrada", entregaId)));
    }

    @Transactional
    public Entrega criar(Entrega entrega) {
        Cliente cliente = this.clienteService.findClienteById(entrega.getCliente().getId());

        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        entrega.setCliente(cliente);

        return this.entregaRepository.save(entrega);
    }

    public void update(Entrega entrega) {
        Optional<Entrega> entregaOptional = this.entregaRepository.findById(entrega.getId());

        if(entregaOptional.isPresent()) {
            if(entregaOptional.get().getDataFinalizacao() == null)
                this.entregaRepository.save(entrega);
            else
                throw new RequisicaoInvalidaException(String.format("A entrega %s já está finalizada", entrega.getId()));
        }
        else
            throw new RecursoNaoEncontradoException(String.format("Entrega com id %s não existe", entrega.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        try {
            this.entregaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException(String.format("Entrega com id %s não existe", id));
        }
    }
}
