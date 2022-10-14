package org.almox.modules.operador.dto;

import org.almox.modules.pessoa.model.UF;

import java.util.Set;
import java.util.UUID;

public class AprovarSolicitacaoCadastroDTO {
    public Set<String> funcoes;
    public String email;
    public String telefone;
    public String logradouro;
    public String complemento;
    public String numero;
    public String cep;
    public String cidade;
    public UF uf;
    public String bairro;
}
