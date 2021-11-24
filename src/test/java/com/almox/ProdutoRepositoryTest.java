package com.almox;

import com.almox.model.entidades.*;
import com.almox.model.enums.TipoEndereco;
import com.almox.model.enums.TipoTelefone;
import com.almox.model.enums.UnidadeMedida;
import com.almox.repositories.GrupoRepository;
import com.almox.repositories.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    private Produto produto;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired GrupoRepository grupoRepository;


    @Test
    public void ShouldFindProductsByNameContaining(){

        LocalDateTime data = LocalDateTime.now();

        List<String> palavrasChave = new ArrayList<String>();
        palavrasChave.add("DETALHES");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("USUARIO");
        Set<Usuario> usuariosSetList = new HashSet<Usuario>();
        usuariosSetList.add(usuario);


        Contato contato = new Contato();
        contato.setBairro("BAIRRO");

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
        contato.setTipoEndereco(TipoEndereco.COMERCIAL);

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setCnpj("1234567891011");
        fornecedor.setNomeFantasia("FORNECEDOR");
        fornecedor.setRazaoSocial("NOME FANTASIA");
        fornecedor.setContato(contato);
        fornecedor.setDataCriacao(data);
        fornecedor.setCriadoPor(usuario);
        fornecedor.setDataAlteracao(data);
        fornecedor.setDataExclusao(data);
        fornecedor.setExcluidoPor(usuario);
        fornecedor.setAlteradoPor(usuario);
        Set<Fornecedor> fornecedoresProduto = new HashSet<Fornecedor>();
        fornecedoresProduto.add(fornecedor);

        Fabricante fabricante = new Fabricante();
        fabricante.setId(1L);

        fabricante.setCnpj("1234567891011");
        fabricante.setContato(contato);
        fabricante.setDataCriacao(data);
        fabricante.setCriadoPor(usuario);
        fabricante.setDataAlteracao(data);
        fabricante.setDataExclusao(data);
        fabricante.setExcluidoPor(usuario);
        fabricante.setAlteradoPor(usuario);
        fabricante.setRazaoSocial("RAZAO-SOCIAL");
        fabricante.setNomeFantasia("NOME-FANTASIA");



        Departamento departamento = new Departamento();

        departamento.setUsuarios(usuariosSetList);
        departamento.setNome("DEPARTAMENTO");
        departamento.setDataCriacao(data);
        departamento.setCriadoPor(usuario);
        departamento.setDataAlteracao(data);
        departamento.setDataExclusao(data);
        departamento.setExcluidoPor(usuario);
        departamento.setAlteradoPor(usuario);
        Set<Departamento> departamentosSetList = new HashSet<>();
        departamentosSetList.add(departamento);

        ItemOrcamentoDepartamento itemOrcamentoDepartamento = new ItemOrcamentoDepartamento();

        itemOrcamentoDepartamento.setDataLancamento(data);
        itemOrcamentoDepartamento.setValor(BigDecimal.valueOf(1200.00));
        List<ItemOrcamentoDepartamento> itensOrcamentoDepartamento = new ArrayList<ItemOrcamentoDepartamento>();


        OrcamentoDepartamento orcamentoDepartamento = new OrcamentoDepartamento();
        orcamentoDepartamento.setItensPertencentes(itensOrcamentoDepartamento);
        orcamentoDepartamento.setDepartamento(departamento);

        orcamentoDepartamento.setValorLimite(BigDecimal.ZERO);
        orcamentoDepartamento.setDataInicio(LocalDate.now());


        List<OrcamentoDepartamento> orcamentoDepartamentoList = new ArrayList<>();
        orcamentoDepartamentoList.add(orcamentoDepartamento);

        departamento.setOrcamentos(orcamentoDepartamentoList);

        itemOrcamentoDepartamento.setOrcamentoPertencente(orcamentoDepartamento);
        itensOrcamentoDepartamento.add(itemOrcamentoDepartamento);

        Grupo grupo = new Grupo();
        grupo.setId(1L);

        grupo.setDescricao("FINANCEIRO");
        grupo.setDataCriacao(data);
        grupo.setCriadoPor(usuario);
        grupo.setDataAlteracao(data);
        grupo.setDataExclusao(data);
        grupo.setExcluidoPor(usuario);
        grupo.setAlteradoPor(usuario);


        ConfiguracaoEstoqueProduto configuracaoEstoqueProduto = new ConfiguracaoEstoqueProduto();
        configuracaoEstoqueProduto.setId(1L);
        configuracaoEstoqueProduto.setEstoqueAtual(BigDecimal.ONE);
        configuracaoEstoqueProduto.setEstoqueMaximo(BigDecimal.TEN);
        configuracaoEstoqueProduto.setEstoqueMinimo(BigDecimal.ONE);
        configuracaoEstoqueProduto.setControlaEstoqueMaximo(false);
        configuracaoEstoqueProduto.setControlaEstoqueMinimo(true);

        Produto produto = new Produto();
//produto.setId(1L);
        produto.setDescricao("DESCRIÇÃO");
        produto.setCodigoBarras("CODIGO DE BARRAS");
        produto.setCustoMedio(BigDecimal.valueOf(12.00));
        produto.setPossuiLoteValidade(true);
        produto.setFornecedores(fornecedoresProduto);
        produto.setUnidadeMedida(UnidadeMedida.KG);
        produto.setFabricante(fabricante);
        produto.setDetalhes("DETALHES");
        produto.setPalavrasChave(palavrasChave);
        produto.setDepartamentos(departamentosSetList);
        produto.setGrupo(grupo);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setCriadoPor(usuario);
        produto.setDataAlteracao(LocalDateTime.now());
        produto.setDataExclusao(LocalDateTime.now());
        produto.setExcluidoPor(usuario);
        produto.setAlteradoPor(usuario);
       // configuracaoEstoqueProduto.setProduto(produto);
        produto.setConfiguracaoEstoque(configuracaoEstoqueProduto);
        configuracaoEstoqueProduto.setProduto(produto);

        Set<Produto> produtoSetList = new HashSet<Produto>();
        produtoSetList.add(produto);

        List<Produto> produtosLista = new ArrayList<Produto>();
        produtosLista.add(produto);
        grupo.setProdutos(produtosLista);

        departamento.setProdutos(produtoSetList);
        fabricante.setProdutosFornecidos(produtoSetList);

        produto.setGrupo(grupoRepository.save(produto.getGrupo()));
     entityManager.persist(produto);

     produtoRepository.save(produto);

    }
}
