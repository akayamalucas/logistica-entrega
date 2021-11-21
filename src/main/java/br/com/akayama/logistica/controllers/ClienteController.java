package br.com.akayama.logistica.controllers;

import br.com.akayama.logistica.data.converter.ClienteDtoConverter;
import br.com.akayama.logistica.data.dto.ClienteEntrada;
import br.com.akayama.logistica.data.dto.ClienteResposta;
import br.com.akayama.logistica.data.entity.Cliente;
import br.com.akayama.logistica.data.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/clientes")
public class ClienteController {

    private ClienteService clienteService;
    private ClienteDtoConverter clienteDtoConverter;

    public ClienteController(ClienteService clienteService, ClienteDtoConverter clienteDtoConverter) {
        this.clienteService = clienteService;
        this.clienteDtoConverter = clienteDtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResposta>> listarTodos() {
        return new ResponseEntity<>(this.clienteDtoConverter.toClienteDtoCollection(this.clienteService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResposta> listarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(this.clienteDtoConverter.toClienteDto(this.clienteService.findClienteById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteResposta> criar(@RequestBody @Valid ClienteEntrada clienteEntradaDto) {
        Cliente clienteEntrada = this.clienteDtoConverter.toDomainEntity(clienteEntradaDto);
        Cliente clienteCriado = this.clienteService.save(clienteEntrada);
        ClienteResposta clienteResposta = this.clienteDtoConverter.toClienteDto(clienteCriado);

        return new ResponseEntity<>(clienteResposta, HttpStatus.CREATED);
    }

    @PutMapping
    public void atualizar(@RequestBody @Valid ClienteEntrada clienteEntradaDto) {
        Cliente clienteEntrada = this.clienteDtoConverter.toDomainEntity(clienteEntradaDto);

        this.clienteService.update(clienteEntrada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        this.clienteService.deleteById(id);
    }
}
