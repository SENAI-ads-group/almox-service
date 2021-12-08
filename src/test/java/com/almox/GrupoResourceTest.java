package com.almox;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.almox.model.entidades.*;
import com.almox.model.enums.StatusAuditavel;
import com.almox.model.enums.TipoEndereco;
import com.almox.model.enums.TipoTelefone;
import com.almox.model.enums.UnidadeMedida;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class GrupoResourceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void grupoInsertTest(){

        LocalDateTime data = LocalDateTime.now();


        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            List<String> palavrasChave = new ArrayList<String>();
            palavrasChave.add("DETALHES");

            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setLogin("USUARIO");
            Set<Usuario> usuariosSetList = new HashSet<Usuario>();
            usuariosSetList.add(usuario);


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
            contato.setTipoEndereco(TipoEndereco.COMERCIAL);

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(1L);
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
            departamento.setId(1L);
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
            itemOrcamentoDepartamento.setId(1L);
            itemOrcamentoDepartamento.setDataLancamento(data);
            itemOrcamentoDepartamento.setValor(BigDecimal.valueOf(1200.00));
            List<ItemOrcamentoDepartamento> itensOrcamentoDepartamento = new ArrayList<ItemOrcamentoDepartamento>();


            OrcamentoDepartamento orcamentoDepartamento = new OrcamentoDepartamento();
            orcamentoDepartamento.setItensPertencentes(itensOrcamentoDepartamento);
            orcamentoDepartamento.setDepartamento(departamento);
            orcamentoDepartamento.setId(1L);
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
            produto.setId(1L);
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
            configuracaoEstoqueProduto.setProduto(produto);
            produto.setConfiguracaoEstoque(configuracaoEstoqueProduto);
            configuracaoEstoqueProduto.setProduto(produto);

            Set<Produto> produtoSetList = new HashSet<Produto>();
            produtoSetList.add(produto);

            List<Produto> produtosLista = new ArrayList<Produto>();
            produtosLista.add(produto);
            grupo.setProdutos(produtosLista);

            departamento.setProdutos(produtoSetList);
            fabricante.setProdutosFornecidos(produtoSetList);


            String requestJson = mapper.writeValueAsString(produto);
            // @formatter:off
            mvc.perform(post("/produtos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            // @formatter:on
        }catch (Exception e) {
            fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
        }

    }

    @Test
    void grupoUpdateTest() {
        try {
            Grupo grupo = new Grupo();
            grupo.setId(1L);
            grupo.setDescricao("Contabilidade");
            grupo.setCriadoPor(new Usuario(1L,"marcos"));

            String requestJson = new ObjectMapper().writeValueAsString(grupo);
            // @formatter:off
            mvc.perform(put("/grupos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            // @formatter:on
        } catch (Exception e) {
            fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
    }

    @Test
    void grupoDeleteTest() {
        try {
            // @formatter:off
            mvc.perform(delete("/grupos/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
            // @formatter:on
        } catch (Exception e) {
            fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
    }}

    /*private Usuario gerarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("USUARIO");

        return usuario;
    }

    /*private Contato gerarContato(){
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
        contato.setTipoEndereco(TipoEndereco.COMERCIAL);
        return contato;
    }

    private Fabricante gerarFabricante(){
        Set<Produto> produtoList = new HashSet<Produto>();
        produtoList.add(gerarProduto());

        Fabricante fabricante = new Fabricante();
        fabricante.setId(1L);
        fabricante.setCnpj("1234567891011");
        fabricante.setContato(gerarContato());
        fabricante.setDataCriacao(LocalDateTime.now());
        fabricante.setCriadoPor(new Usuario(1L,"marcos"));
        fabricante.setDataAlteracao(LocalDateTime.now());
        fabricante.setDataExclusao(LocalDateTime.now());
        fabricante.setExcluidoPor(new Usuario(2L,"carlos"));
        fabricante.setAlteradoPor(new Usuario(3L,"jonas"));
        fabricante.setRazaoSocial("RAZAO-SOCIAL");
        fabricante.setNomeFantasia("NOME-FANTASIA");
        fabricante.setProdutosFornecidos(produtoList);
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
        fornecedor.setDataCriacao(LocalDateTime.now());
        fornecedor.setCriadoPor(new Usuario(1L,"marcos"));
        fornecedor.setDataAlteracao(LocalDateTime.now());
        fornecedor.setDataExclusao(LocalDateTime.now());
        fornecedor.setExcluidoPor(new Usuario(2L,"carlos"));
        fornecedor.setAlteradoPor(new Usuario(3L,"jonas"));

        return fornecedor;
    }

    private Grupo gerarGrupo(){
        List<Produto> produtoList = new ArrayList<Produto>();
        produtoList.add(gerarProduto());

        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setDescricao("FINANCEIRO");
        grupo.setDataCriacao(LocalDateTime.now());
        grupo.setCriadoPor(new Usuario(1L,"marcos"));
        grupo.setDataAlteracao(LocalDateTime.now());
        grupo.setDataExclusao(LocalDateTime.now());
        grupo.setExcluidoPor(new Usuario(2L,"carlos"));
        grupo.setAlteradoPor(new Usuario(3L,"jonas"));
        grupo.setProdutos(produtoList);
        return grupo;
    }

    private Departamento gerarDepartamento(){
        Set<Usuario> usuariosDepartamento = new HashSet<Usuario>();
        usuariosDepartamento.add(gerarUsuario());

        Set<Produto> produtoList = new HashSet<Produto>();
        produtoList.add(gerarProduto());

        List<OrcamentoDepartamento> orcamentoDepartamentoList = new ArrayList<>();
        orcamentoDepartamentoList.add(gerarOrcamentoDepartamento());


        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setUsuarios(usuariosDepartamento);
        departamento.setNome("DEPARTAMENTO");
        departamento.setDataCriacao(LocalDateTime.now());
        departamento.setCriadoPor(new Usuario(1L,"marcos"));
        departamento.setDataAlteracao(LocalDateTime.now());
        departamento.setDataExclusao(LocalDateTime.now());
        departamento.setExcluidoPor(new Usuario(2L,"carlos"));
        departamento.setAlteradoPor(new Usuario(3L,"jonas"));
        departamento.setProdutos(produtoList);
        departamento.setOrcamentos(orcamentoDepartamentoList);

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
        configuracaoEstoqueProduto.setProduto(gerarProduto());

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
        produto.setDataCriacao(LocalDateTime.now());
        produto.setCriadoPor(new Usuario(1L,"marcos"));
        produto.setDataAlteracao(LocalDateTime.now());
        produto.setDataExclusao(LocalDateTime.now());
        produto.setExcluidoPor(new Usuario(2L,"carlos"));
        produto.setAlteradoPor(new Usuario(3L,"jonas"));

      //produto.setConfiguracaoEstoque(gerarConfiguracaoEstoqueProduto());

        return produto;
    }

}*/
