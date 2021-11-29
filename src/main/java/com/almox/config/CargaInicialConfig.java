package com.almox.config;

import com.almox.model.entidades.Contato;
import com.almox.model.entidades.Fabricante;
import com.almox.model.entidades.Fornecedor;
import com.almox.model.entidades.Grupo;
import com.almox.model.enums.TipoEndereco;
import com.almox.model.enums.TipoTelefone;
import com.almox.services.FabricanteService;
import com.almox.services.FornecedorService;
import com.almox.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

@Configuration
public class CargaInicialConfig implements CommandLineRunner {

    private final FabricanteService fabricanteService;
    private final FornecedorService fornecedorService;
    private final GrupoService grupoService;

    @Autowired
    public CargaInicialConfig(FabricanteService fabricanteService, FornecedorService fornecedorService, GrupoService grupoService) {
        this.fabricanteService = fabricanteService;
        this.fornecedorService = fornecedorService;
        this.grupoService = grupoService;
    }

    @Override
    public void run(String... args) throws Exception {
        SecurityContextHolder.setContext(buildContext());
//        cargaFabricantes();
//        cargaFornecedores();
//        cargaGrupos();
    }

    private SecurityContext buildContext() {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "admin";
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
        SecurityContext context = new SecurityContext() {
            @Override
            public Authentication getAuthentication() {
                return authentication;
            }

            @Override
            public void setAuthentication(Authentication authentication) {

            }
        };
        return context;
    }

    private void cargaFabricantes() {
        var fa1 = Fabricante.builder()
                .cnpj("64942759000176")
                .nomeFantasia("Fabricante 1 LTDA")
                .razaoSocial("Fabricante 1 LTDA SA")
                .contato(contatoPadrao())
                .build();

        var fa2 = Fabricante.builder()
                .cnpj("26871194000194")
                .nomeFantasia("Fabricante 2 LTDA")
                .razaoSocial("Fabricante 2 LTDA SA")
                .contato(contatoPadrao())
                .build();

        var fa3 = Fabricante.builder()
                .cnpj("33716089000146")
                .nomeFantasia("Fabricante 3 LTDA")
                .razaoSocial("Fabricante 3 LTDA SA")
                .contato(contatoPadrao())
                .build();
        List.of(fa1, fa2, fa3).forEach(fabricanteService::criar);
    }

    private void cargaFornecedores() {
        var fo1 = Fornecedor.builder()
                .cnpj("57460245000119")
                .nomeFantasia("Fornecedor 1 LTDA")
                .razaoSocial("Fornecedor 1 LTDA SA")
                .contato(contatoPadrao())
                .build();

        var fo2 = Fornecedor.builder()
                .cnpj("94868098000147")
                .nomeFantasia("Fornecedor 2 LTDA")
                .razaoSocial("Fornecedor 2 LTDA SA")
                .contato(contatoPadrao())
                .build();

        var fo3 = Fornecedor.builder()
                .cnpj("60205886000131")
                .nomeFantasia("Fornecedor 3 LTDA")
                .razaoSocial("Fornecedor 3 LTDA SA")
                .contato(contatoPadrao())
                .build();
        List.of(fo1, fo2, fo3).forEach(fornecedorService::criar);
    }

    private Contato contatoPadrao() {
        return Contato.builder()
                .cep("75380082")
                .logradouro("Rua Padrão")
                .numero("1")
                .complemento("Perto da praça")
                .bairro("Bairro da Tijuca")
                .cidade("Goiânia")
                .estado("GO")
                .telefone1("62999999999")
                .telefone2("62444444444")
                .email1("contato@contato.com.br")
                .email2("contato@email.com")
                .tipoEndereco(TipoEndereco.COMERCIAL)
                .tipoTelefone1(TipoTelefone.COMERCIAL)
                .tipoTelefone2(TipoTelefone.COMERCIAL)
                .build();
    }

    private void cargaGrupos() {
        var g1 = Grupo.builder()
                .descricao("Material de Limpeza")
                .build();

        var g2 = Grupo.builder()
                .descricao("E.P.I.")
                .build();

        var g3 = Grupo.builder()
                .descricao("Material de Escritório")
                .build();

        var g4 = Grupo.builder()
                .descricao("Utilitários")
                .build();
        List.of(g1, g2, g3, g4).forEach(grupoService::criar);
    }

}
