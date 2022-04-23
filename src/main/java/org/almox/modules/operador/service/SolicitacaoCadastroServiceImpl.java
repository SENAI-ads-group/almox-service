package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.operador.dto.AprovarSolicitacaoCadastroDTO;
import org.almox.modules.operador.model.Funcao;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.model.SolicitacaoCadastro;
import org.almox.modules.operador.repository.FuncaoRepository;
import org.almox.modules.operador.repository.OperadorRepository;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SolicitacaoCadastroServiceImpl implements SolicitacaoCadastroService {

    private final RedissonClient redissonClient;
    private final BCryptPasswordEncoder passwordEncoder;
    private final OperadorService operadorService;
    private final OperadorRepository operadorRepository;
    private RMapCache<String, SolicitacaoCadastro> rMapCache;

    @PostConstruct
    public void init() {
        rMapCache = redissonClient.getMapCache("almox-solicitacoes-cadastro");
    }

    @Override
    public void criar(SolicitacaoCadastro solicitacaoCadastro) {
        operadorRepository.buscarPorCpfOuEmailPessoa(solicitacaoCadastro.cpf, solicitacaoCadastro.email)
                .ifPresent(operadorJaCadastrado -> {
                    throw new RegraNegocioException("${operador_ja_cadastrado}");
                });
        solicitacaoCadastro.dataCriacao = LocalDateTime.now();
        solicitacaoCadastro.senha = passwordEncoder.encode(solicitacaoCadastro.senha);
        rMapCache.remove(solicitacaoCadastro.cpf);
        rMapCache.put(solicitacaoCadastro.cpf, solicitacaoCadastro, 30, TimeUnit.DAYS);
    }

    @Override
    public Operador aprovar(String cpf, AprovarSolicitacaoCadastroDTO aprovarSolicitacaoCadastro) {
        SolicitacaoCadastro solicitacaoCadastro = Optional.ofNullable(rMapCache.get(cpf))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${solicitacao_cadastro_nao_encontrada}"));
        Operador operadorASerCriado = Operador.builder()
                .login(solicitacaoCadastro.cpf)
                .senha(solicitacaoCadastro.senha)
                .funcoes(aprovarSolicitacaoCadastro.funcoes
                                .stream()
                                .map(f -> Funcao.builder().nome(f).build())
                                .collect(Collectors.toSet())
                )
                .pessoa(PessoaFisica.builder()
                        .cpf(solicitacaoCadastro.cpf)
                        .nome(solicitacaoCadastro.nome)
                        .email(solicitacaoCadastro.email)
                        .cep(aprovarSolicitacaoCadastro.cep)
                        .logradouro(aprovarSolicitacaoCadastro.logradouro)
                        .bairro(aprovarSolicitacaoCadastro.bairro)
                        .cidade(aprovarSolicitacaoCadastro.cidade)
                        .numero(aprovarSolicitacaoCadastro.numero)
                        .complemento(aprovarSolicitacaoCadastro.complemento)
                        .telefone(aprovarSolicitacaoCadastro.telefone)
                        .uf(aprovarSolicitacaoCadastro.uf)
                        .build()
                )
                .build();
        Operador operadorCriado = operadorService.criar(operadorASerCriado);
        rMapCache.remove(cpf);
        return operadorCriado;
    }

    @Override
    public Set<SolicitacaoCadastro> buscar() {
        return rMapCache.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public void excluir(String cpf) {
        Optional.ofNullable(rMapCache.get(cpf))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${solicitacao_cadastro_nao_encontrada}"));
        rMapCache.remove(cpf);
    }
}
