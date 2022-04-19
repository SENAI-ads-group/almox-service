package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.operador.OperadorLogado;
import org.almox.modules.operador.dto.OperadorFiltroDTO;
import org.almox.modules.operador.model.Funcao;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.repository.FuncaoRepository;
import org.almox.modules.operador.repository.OperadorRepository;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ApplicationScope
public class OperadorServiceImpl implements OperadorService {

    private final OperadorRepository operadorRepository;
    private final FuncaoRepository funcaoRepository;
    private final PessoaService pessoaService;
    private final ValidatorAutoThrow validator;
    private final BCryptPasswordEncoder passwordEncoder;

    @ApplicationScope
    @OperadorLogado
    @Bean
    public Operador getOperadorLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;
        String login = authentication.getPrincipal().toString();
        return operadorRepository.findByLoginEquals(login).orElse(null);
    }

    @Override
    @Transactional
    public Operador criar(Operador operador) {
        try {
            PessoaFisica pessoaAssociadaAoOperador = operador.getPessoa().isNew()
                    ? pessoaService.criar(operador.getPessoa())
                    : (PessoaFisica) pessoaService.buscarPorId(operador.getPessoa().getId());
            operador.setPessoa(pessoaAssociadaAoOperador);
        } catch (ClassCastException e) {
            throw new RegraNegocioException("${operador_pessoa_deve_ser_fisica}");
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException("${operador_pessoa_nao_encontrada}");
        }
        funcaoRepository.saveAll(operador.getFuncoes()
                .stream()
                .map(funcao -> funcaoRepository.buscarPorNome(funcao.getNome()).orElse(funcao))
                .collect(Collectors.toSet())
        );
        operador.setSenha(passwordEncoder.encode(operador.getPassword()));
        return operadorRepository.save(operador);
    }

    @Override
    public Operador buscarPorId(UUID id) {
        Operador operadorEncontrado = operadorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${operador_nao_encontrado_por_id}"));
        return operadorEncontrado;
    }

    @Override
    public Operador buscarPorLogin(String login) {
        Operador operadorEncontrado = operadorRepository.findByLoginEquals(login)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${operador_nao_encontrado_por_login}"));
        return operadorEncontrado;
    }

    @Override
    public Optional<Operador> buscarPorLoginOptional(String login) {
        return operadorRepository.findByLoginEquals(login);
    }

    @Override
    public Optional<Operador> buscarPorCpfPessoaOptional(String cpf) {
        return operadorRepository.buscarPorCpfPessoa(cpf);
    }

    @Override
    public List<Operador> buscar(OperadorFiltroDTO filtro, Sort sort) {
        validator.validate(filtro);
        return operadorRepository.buscarPorNomeEmailPessoa(filtro.nome, filtro.email, sort);
    }

    @Override
    public Page<Operador> buscarPaginado(OperadorFiltroDTO filtro, Pageable pageable) {
        validator.validate(filtro);
        return operadorRepository.buscarPorNomeEmailPessoa(filtro.nome, filtro.email, pageable);
    }

    @Override
    @Transactional
    public Operador atualizar(UUID id, Operador operador) {
        Operador operadorEncontrado = buscarPorId(id);
        validator.validate(operador);
        operador.setId(id);
        Operador operadorAtualizado = operadorRepository.save(operador);
        operadorAtualizado.setPessoa(operadorEncontrado.getPessoa());
        return operadorAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        operadorRepository.deleteById(id);
    }

    @Override
    public boolean isAdministrador(Operador operador) {
        Objects.requireNonNull(operador);
        Funcao funcaoAdministrador = funcaoRepository.buscarFuncaoAdministrador();
        boolean contemFuncaoAdministrador = colecaoVaziaCasoSejaNula(operador.getFuncoes()).stream()
                .map(Funcao::getId)
                .anyMatch(funcId -> funcaoAdministrador.getId().equals(funcId));
        return contemFuncaoAdministrador;
    }

    @Override
    public boolean isAlmoxarife(Operador operador) {
        Objects.requireNonNull(operador);
        Funcao funcaoAlmoxarife = funcaoRepository.buscarFuncaoAlmoxarife();
        boolean contemFuncaoAlmoxarife = colecaoVaziaCasoSejaNula(operador.getFuncoes()).stream()
                .map(Funcao::getId)
                .anyMatch(funcId -> funcaoAlmoxarife.getId().equals(funcId));
        return contemFuncaoAlmoxarife;
    }

}
