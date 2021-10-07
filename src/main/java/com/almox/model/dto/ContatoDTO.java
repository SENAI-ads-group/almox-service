package com.almox.model.dto;

import com.almox.model.entidades.Contato;
import com.almox.model.enums.TipoEndereco;
import com.almox.model.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO extends EntidadeDTO<Contato, ContatoDTO> {
    public static final ContatoDTO INSTANCIA = new ContatoDTO();

    private Long id;
    private String email;
    private String telefone1;
    private TipoTelefone tipoTelefone1;
    private String telefone2;
    private TipoTelefone tipoTelefone2;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private TipoEndereco tipoEndereco;

    public ContatoDTO(Contato contato) {
        id = contato.getId();
        email = contato.getEmail();
        telefone1 = contato.getTelefone1();
        tipoTelefone1 = contato.getTipoTelefone1();
        telefone2 = contato.getTelefone2();
        tipoTelefone2 = contato.getTipoTelefone2();
        logradouro = contato.getLogradouro();
        complemento = contato.getComplemento();
        numero = contato.getNumero();
        cep = contato.getCep();
        cidade = contato.getCidade();
        estado = contato.getEstado();
        bairro = contato.getBairro();
        tipoEndereco = contato.getTipoEndereco();

    }

    @Override
    public ContatoDTO entidadeParaDTO(Contato entidade) {
        return new ContatoDTO(entidade);
    }

    @Override
    public Contato dtoParaEntidade(ContatoDTO contatoDTO) {
        return new Contato(id, email, telefone1, tipoTelefone1, telefone2, tipoTelefone2,
                logradouro, complemento, numero, cep, cidade, estado, bairro, tipoEndereco);
    }

    @Override
    public List<ContatoDTO> entidadeListParaDTOList(Collection<Contato> contatoList) {
        return contatoList.stream()
                .map(ContatoDTO::new)
                .collect(Collectors.toList());
    }
}
