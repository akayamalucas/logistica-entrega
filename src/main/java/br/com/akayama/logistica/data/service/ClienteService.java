package br.com.akayama.logistica.data.service;

import br.com.akayama.logistica.data.entity.Cliente;
import br.com.akayama.logistica.data.repository.ClienteRepository;
import br.com.akayama.logistica.exception.RequisicaoInvalidaException;
import br.com.akayama.logistica.exception.RecursoNaoEncontradoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService extends AbstractCrudService<Cliente, Integer>{

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    protected ClienteRepository getRepository() {
        return this.clienteRepository;
    }

    public Cliente findClienteById(Integer id) {
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);

        if(clienteOptional.isPresent())
            return clienteOptional.get();
        else
            throw new RecursoNaoEncontradoException(String.format("Cliente com id %s não existe", id));
    }

    @Override
    public Cliente save(Cliente cliente) {
        Optional<Cliente> clienteOptional = this.clienteRepository.findClienteByEmail(cliente.getEmail());

        if(clienteOptional.isPresent()) {
            Cliente cliente1 = clienteOptional.get();
            if(cliente1.equals(cliente))
                return this.clienteRepository.save(cliente);
            else
                throw new RequisicaoInvalidaException("Já existe um cliente cadastrado com esse e-mail");

        }

        return this.clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            this.clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException(String.format("Cliente com id %s não existe", id));
        }
    }

    public void update(Cliente cliente) {
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(cliente.getId());

        if(clienteOptional.isPresent())
            this.clienteRepository.save(cliente);
        else
            throw new RecursoNaoEncontradoException(String.format("Cliente com id %s não existe", cliente.getId()));
    }
}
