package br.com.akayama.logistica.data.converter;

import br.com.akayama.logistica.data.dto.MovimentoEntrada;
import br.com.akayama.logistica.data.dto.MovimentoResposta;
import br.com.akayama.logistica.data.entity.Movimento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovimentoDtoConverter {

    private ModelMapper modelMapper;

    public MovimentoDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Movimento toDomainEntity(MovimentoEntrada movimentoEntrada) {
        return this.modelMapper.map(movimentoEntrada, Movimento.class);
    }

    public List<Movimento> toCollectionDomainEntity(List<MovimentoEntrada> movimentoEntradaList) {
        return movimentoEntradaList.stream().map(this::toDomainEntity).collect(Collectors.toList());
    }

    public MovimentoResposta toRespostaDto(Movimento movimento) {
        return this.modelMapper.map(movimento, MovimentoResposta.class);
    }

    public List<MovimentoResposta> toCollectionRespostaDto(List<Movimento> movimentos) {
        return movimentos.stream().map(this::toRespostaDto).collect(Collectors.toList());
    }
}
