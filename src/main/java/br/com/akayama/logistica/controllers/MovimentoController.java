package br.com.akayama.logistica.controllers;

import br.com.akayama.logistica.data.converter.MovimentoDtoConverter;
import br.com.akayama.logistica.data.dto.MovimentoEntrada;
import br.com.akayama.logistica.data.dto.MovimentoResposta;
import br.com.akayama.logistica.data.entity.Movimento;
import br.com.akayama.logistica.data.service.RegistroMovimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class MovimentoController {

    private RegistroMovimentoService registroMovimentoService;
    private MovimentoDtoConverter movimentoDtoConverter;

    public MovimentoController(RegistroMovimentoService registroMovimentoService, MovimentoDtoConverter movimentoDtoConverter) {
        this.registroMovimentoService = registroMovimentoService;
        this.movimentoDtoConverter = movimentoDtoConverter;
    }

    @PostMapping("/entregas/{entregaId}/movimentos")
    public ResponseEntity<MovimentoResposta> criar(@PathVariable Integer entregaId, @RequestBody @Valid MovimentoEntrada movimentoEntrada) {
        Movimento movimentoRegistrado = this.registroMovimentoService.criar(entregaId, movimentoEntrada.getDescricao());
        MovimentoResposta movimentoResposta = this.movimentoDtoConverter.toRespostaDto(movimentoRegistrado);

        return new ResponseEntity<>(movimentoResposta, HttpStatus.CREATED);
    }

    @GetMapping("/entregas/{entregaId}/movimentos")
    public ResponseEntity<List<MovimentoResposta>> listarTodos(@PathVariable Integer entregaId) {
        List<MovimentoResposta> movimentoRespostaList = this.movimentoDtoConverter.toCollectionRespostaDto(this.registroMovimentoService.listarTodos(entregaId));
        return new ResponseEntity<>(movimentoRespostaList, HttpStatus.OK);
    }
}
