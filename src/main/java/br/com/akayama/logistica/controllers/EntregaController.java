package br.com.akayama.logistica.controllers;

import br.com.akayama.logistica.data.dto.EntregaEntrada;
import br.com.akayama.logistica.data.dto.EntregaResposta;
import br.com.akayama.logistica.data.dto.MovimentoResposta;
import br.com.akayama.logistica.data.entity.Entrega;
import br.com.akayama.logistica.data.service.EntregaService;
import br.com.akayama.logistica.data.converter.EntregaDtoConverter;
import br.com.akayama.logistica.data.service.FinalizacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/entregas")
public class EntregaController {

    private EntregaService entregaService;
    private EntregaDtoConverter entregaDtoConverter;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    public EntregaController(EntregaService entregaService, EntregaDtoConverter entregaDtoConverter, FinalizacaoEntregaService finalizacaoEntregaService) {
        this.entregaService = entregaService;
        this.entregaDtoConverter = entregaDtoConverter;
        this.finalizacaoEntregaService = finalizacaoEntregaService;
    }

    @GetMapping
    public ResponseEntity<List<EntregaResposta>> listarTodos() {
        return new ResponseEntity<>(this.entregaDtoConverter.toRespostaDtoCollection(this.entregaService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResposta> buscar(@PathVariable Integer id) {
        return this.entregaService.findById(id)
                .map(entrega -> new ResponseEntity<>(this.entregaDtoConverter.toRespostaDto(entrega), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntregaResposta> criar(@RequestBody @Valid EntregaEntrada entregaDto) {
        Entrega entrega = this.entregaDtoConverter.toDomainEntity(entregaDto);
        Entrega entregaCriada = this.entregaService.criar(entrega);
        EntregaResposta entregaResposta = this.entregaDtoConverter.toRespostaDto(entregaCriada);
        return new ResponseEntity<>(entregaResposta, HttpStatus.CREATED);
    }

    @PutMapping
    public void atualizar(@RequestBody @Valid EntregaEntrada entregaEntradaDto) {
        Entrega entregaEntrada = this.entregaDtoConverter.toDomainEntity(entregaEntradaDto);

        this.entregaService.update(entregaEntrada);
    }

    @DeleteMapping("/{id}")
    public void deletar(Integer id) {
        this.entregaService.deleteById(id);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Integer entregaId) {
        this.finalizacaoEntregaService.finalizar(entregaId);
    }
}
