package com.almox;

import com.almox.model.entidades.*;
import com.almox.model.enums.TipoTelefone;
import com.almox.model.enums.UnidadeMedida;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
public class Fabrica {


    private Usuario gerarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("USUARIO");

        return usuario;
    }

private Contato gerarContato(){
    Contato contato = new Contato();
    contato.setBairro("BAIRRO");
    contato.setId(1L);
    contato.setCep("CEP");
    contato.setCidade("CIDADE");
    contato.setComplemento("COMPLEMENTO");
    contato.setLogradouro("LOGRADOURO");
    contato.setEmail1("email1");
    contato.setEmail2("email2");
    contato.setEstado("GOIAS");
    contato.setNumero("NUMERO");
    contato.setComplemento("COMPLEMENTO");
    contato.setTelefone1("TELEFONE1");
    contato.setTelefone2("TELEFONE2");
    contato.setTipoTelefone1(TipoTelefone.FIXO);
    contato.setTipoTelefone2(TipoTelefone.FIXO);
    return contato;
}

private Fabricante gerarFabricante(){
    Fabricante fabricante = new Fabricante();
    fabricante.setId(1L);
    fabricante.setCnpj("1234567891011");
    fabricante.setContato(gerarContato());
    //fabricante.setProdutosFornecidos();
    return fabricante;
}

private Fornecedor gerarFornecedor(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        fornecedor.setCnpj("1234567891011");
        fornecedor.setNomeFantasia("FORNECEDOR");
        fornecedor.setRazaoSocial("NOME FANTASIA");
        fornecedor.setContato(gerarContato());

        return fornecedor;
}

private Grupo gerarGrupo(){
    Grupo grupo = new Grupo();
    grupo.setId(1L);
    grupo.setDescricao("FINANCEIRO");
    grupo.setDataCriacao(LocalDateTime.now());
    grupo.setCriadoPor(new Usuario(1L,"marcos"));
    grupo.setDataAlteracao(LocalDateTime.now());
    grupo.setDataExclusao(LocalDateTime.now());
    grupo.setExcluidoPor(new Usuario(2L,"carlos"));
    grupo.setAlteradoPor(new Usuario(3L,"jonas"));
    //grupo.setProdutos(produtoList.add());
    return grupo;
}

private Departamento gerarDepartamento(){
        Set<Usuario> usuariosDepartamento = new HashSet<Usuario>();

        usuariosDepartamento.add(gerarUsuario());

    Departamento departamento = new Departamento();
    departamento.setId(1L);
    departamento.setUsuarios(usuariosDepartamento);
    departamento.setNome("DEPARTAMENTO");

    return departamento;
}

private ItemOrcamentoDepartamento gerarItemOrcamentoDepartamento(OrcamentoDepartamento orcamentoDepartamento){
        ItemOrcamentoDepartamento itemOrcamentoDepartamento = new ItemOrcamentoDepartamento();
        itemOrcamentoDepartamento.setId(1L);
        itemOrcamentoDepartamento.setDataLancamento(LocalDateTime.now());
        itemOrcamentoDepartamento.setValor(BigDecimal.valueOf(1200.00));
        itemOrcamentoDepartamento.setOrcamentoPertencente(orcamentoDepartamento);

        return itemOrcamentoDepartamento;
}

private OrcamentoDepartamento gerarOrcamentoDepartamento(){
        List<ItemOrcamentoDepartamento> itensOrcamentoDepartamento = new ArrayList<ItemOrcamentoDepartamento>();


    OrcamentoDepartamento orcamentoDepartamento = new OrcamentoDepartamento();
    itensOrcamentoDepartamento.add(gerarItemOrcamentoDepartamento(orcamentoDepartamento));
    orcamentoDepartamento.setItensPertencentes(itensOrcamentoDepartamento);
    orcamentoDepartamento.setDepartamento(gerarDepartamento());
    orcamentoDepartamento.setId(1L);
    orcamentoDepartamento.setValorLimite(BigDecimal.ZERO);
    orcamentoDepartamento.setDataInicio(LocalDate.now());

    return orcamentoDepartamento;

}



private ConfiguracaoEstoqueProduto gerarConfiguracaoEstoqueProduto(){
    ConfiguracaoEstoqueProduto configuracaoEstoqueProduto = new ConfiguracaoEstoqueProduto();

    configuracaoEstoqueProduto.setId(1L);
    configuracaoEstoqueProduto.setProduto(gerarProduto());
    configuracaoEstoqueProduto.setEstoqueAtual(BigDecimal.ONE);
    configuracaoEstoqueProduto.setEstoqueMaximo(BigDecimal.TEN);
    configuracaoEstoqueProduto.setEstoqueMinimo(BigDecimal.ONE);
    configuracaoEstoqueProduto.setControlaEstoqueMaximo(false);
    configuracaoEstoqueProduto.setControlaEstoqueMinimo(true);

    return configuracaoEstoqueProduto;
    }

public Produto gerarProduto(){
        Set<Fornecedor> fornecedoresProduto = new HashSet<Fornecedor>();
        fornecedoresProduto.add(gerarFornecedor());

        Set<Departamento> departamentosProduto = new HashSet<Departamento>();
        departamentosProduto.add(gerarDepartamento());

        List<String> palavrasChave = new ArrayList<String>();

        palavrasChave.add("DETALHES");

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setDescricao("DESCRIÇÃO");
        produto.setCodigoBarras("CODIGO DE BARRAS");
        produto.setCustoMedio(BigDecimal.valueOf(12.00));
        produto.setPossuiLoteValidade(true);
        produto.setFornecedores(fornecedoresProduto);
        produto.setUnidadeMedida(UnidadeMedida.KG);
        produto.setFabricante(gerarFabricante());
        produto.setDetalhes("DETALHES");
        produto.setPalavrasChave(palavrasChave);
        produto.setDepartamentos(departamentosProduto);
        produto.setGrupo(gerarGrupo());
        produto.setConfiguracaoEstoque(gerarConfiguracaoEstoqueProduto());

        return produto;
}

    public static void main(String[] args) {

    }


}
