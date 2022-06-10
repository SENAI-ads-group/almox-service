package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.operador.dto.FiltroOperador;
import org.almox.modules.operador.model.Funcao;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.repository.FuncaoRepository;
import org.almox.modules.operador.repository.OperadorRepository;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.almox.modules.util.ColecaoUtil.colecaoVaziaCasoSejaNula;
import static org.almox.modules.util.StringUtil.apenasNumeros;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperadorServiceImpl implements OperadorService {

    private final Validator validator;
    private final OperadorRepository operadorRepository;
    private final FuncaoRepository funcaoRepository;
    private final PessoaService pessoaService;
    private final BCryptPasswordEncoder passwordEncoder;

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
        operador.setFuncoes(operador.getFuncoes()
                .stream()
                .map(funcao -> funcaoRepository.buscarPorNome(funcao.getNome())
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("${funcao_nao_encontrada}")))
                .collect(Collectors.toSet()));
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
        Operador operadorEncontrado = operadorRepository.buscarPorLogin(login)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${operador_nao_encontrado_por_login}"));
        return operadorEncontrado;
    }

    @Override
    public Optional<Operador> buscarPorLoginOptional(String login) {
        return operadorRepository.buscarPorLogin(login);
    }

    @Override
    public Optional<Operador> buscarPorCpfPessoaOptional(String cpf) {
        return operadorRepository.buscarPorCpfPessoa(cpf);
    }

    @Override
    public Page<Operador> buscar(FiltroOperador filtro, Pageable paginacao) {
        filtro.cpf = apenasNumeros(filtro.cpf);
        validator.validate(filtro);
        return operadorRepository.buscarPorNomeEmailPessoa(filtro.nome, filtro.email, filtro.cpf, paginacao);
    }

    @Override
    public Page<Operador> buscarAlmoxarifes(FiltroOperador filtro, Pageable paginacao) {
        filtro.cpf = apenasNumeros(filtro.cpf);
        validator.validate(filtro);
        return operadorRepository.buscarAlmoxarifesPorNomeEmailPessoa(filtro.nome, filtro.email, filtro.cpf, paginacao);
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
