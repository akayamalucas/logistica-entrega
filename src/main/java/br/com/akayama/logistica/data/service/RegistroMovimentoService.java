package br.com.akayama.logistica.data.service;

import br.com.akayama.logistica.data.entity.Entrega;
import br.com.akayama.logistica.data.entity.Movimento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroMovimentoService {

    private EntregaService entregaService;

    public RegistroMovimentoService(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @Transactional
    public Movimento criar(Integer entregaId, String descricao) {
        Entrega entrega = this.entregaService.findEntregaById(entregaId);

        return entrega.adicionarMovimento(descricao);

    }

    public List<Movimento> listarTodos(Integer entregaId) {
        Entrega entrega = this.entregaService.findEntregaById(entregaId);

        return entrega.getMovimentos();
    }
}
