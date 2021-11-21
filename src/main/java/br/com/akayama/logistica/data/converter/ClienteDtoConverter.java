package br.com.akayama.logistica.data.converter;

import br.com.akayama.logistica.data.dto.ClienteEntrada;
import br.com.akayama.logistica.data.dto.ClienteResposta;
import br.com.akayama.logistica.data.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteDtoConverter {

    private ModelMapper modelMapper;

    public ClienteDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteResposta toClienteDto(Cliente cliente) {
        return this.modelMapper.map(cliente, ClienteResposta.class);
    }

    public List<ClienteResposta> toClienteDtoCollection(List<Cliente> clientes) {
        return clientes.stream().map(this::toClienteDto).collect(Collectors.toList());
    }

    public Cliente toDomainEntity(ClienteEntrada clienteEntrada) {
        return this.modelMapper.map(clienteEntrada, Cliente.class);
    }

    public List<Cliente> toDomainEntityCollection(List<ClienteEntrada> clienteEntradaList) {
        return clienteEntradaList.stream().map(this::toDomainEntity).collect(Collectors.toList());
    }
}
