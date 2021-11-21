package br.com.akayama.logistica.data.converter;

import br.com.akayama.logistica.data.dto.EntregaEntrada;
import br.com.akayama.logistica.data.dto.EntregaResposta;
import br.com.akayama.logistica.data.entity.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaDtoConverter {

    private ModelMapper modelMapper;

    public EntregaDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EntregaResposta toRespostaDto(Entrega entrega) {
        return this.modelMapper.map(entrega, EntregaResposta.class);
    }

    public List<EntregaResposta> toRespostaDtoCollection(List<Entrega> entregas) {
        return entregas.stream().map(entrega -> this.modelMapper.map(entrega, EntregaResposta.class)).collect(Collectors.toList());
    }

    public Entrega toDomainEntity(EntregaEntrada entregaEntrada) {
        return this.modelMapper.map(entregaEntrada, Entrega.class);
    }

    public List<Entrega> toDomainEntityCollection(List<EntregaEntrada> entregaEntradaList) {
        return entregaEntradaList.stream().map(entregaEntrada -> this.modelMapper.map(entregaEntrada, Entrega.class)).collect(Collectors.toList());
    }


}
