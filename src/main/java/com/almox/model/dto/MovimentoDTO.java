package com.almox.model.dto;

import com.almox.model.entidades.Movimento;
import com.almox.model.enums.TipoDeMovimento;
import com.almox.model.enums.TipoOrigemMovimento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MovimentoDTO extends AuditavelDTO<Movimento,MovimentoDTO>{

    private Long id;
    private LocalDate data;
    private TipoDeMovimento tipoDeMovimento;
    private Long idOrigem;
    private TipoOrigemMovimento tipoOrigemMovimento;
   //private Set<ItemMovimento> itensMovimento = new HashSet<>();

    public MovimentoDTO(Movimento movimento){
        super(movimento);
        id=movimento.getId();
        data = movimento.getData();
        tipoDeMovimento = movimento.getTipoDeMovimento();
        idOrigem = movimento.getIdOrigem();
        tipoOrigemMovimento = getTipoOrigemMovimento();
    }

    @Override
    public MovimentoDTO entidadeParaDTO(Movimento entidade) {
        return new MovimentoDTO(entidade);
    }

    @Override
    public Movimento dtoParaEntidade(MovimentoDTO movimentoDTO) {
        return new Movimento(movimentoDTO.getId(), movimentoDTO.getData(), movimentoDTO.getTipoDeMovimento(),
                movimentoDTO.getIdOrigem(), movimentoDTO.getTipoOrigemMovimento());
    }

    @Override
    public List<MovimentoDTO> entidadeListParaDTOList(Collection<Movimento> movimentoList) {
        return movimentoList.stream()
                .map(MovimentoDTO::new)
                .collect(Collectors.toList());
    }
}
