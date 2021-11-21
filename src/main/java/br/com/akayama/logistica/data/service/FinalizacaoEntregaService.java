package br.com.akayama.logistica.data.service;

import br.com.akayama.logistica.data.entity.Entrega;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizacaoEntregaService {

    private EntregaService entregaService;

    public FinalizacaoEntregaService(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    public EntregaService getEntregaService() {
        return entregaService;
    }

    public void setEntregaService(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @Transactional
    public void finalizar(Integer entregaId) {
        Entrega entrega = this.entregaService.findEntregaById(entregaId);

        entrega.finalizar();

        this.entregaService.save(entrega);
    }
}
