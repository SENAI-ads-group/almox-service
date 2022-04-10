package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.modules.operador.dto.OperadorFiltroDTO;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.repository.FuncaoRepository;
import org.almox.modules.operador.repository.OperadorRepository;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.service.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperadorServiceImpl implements OperadorService {

    private final OperadorRepository repository;
    private final FuncaoRepository funcaoRepository;
    private final IPessoaService pessoaService;
    private final ValidatorAutoThrow validator;
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
        funcaoRepository.saveAll(operador.getFuncoes());
        operador.setSenha(passwordEncoder.encode(operador.getPassword()));
        return repository.save(operador);
    }

    @Override
    public Operador buscarPorId(UUID id) {
        Operador operadorEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());
        return operadorEncontrado;
    }

    @Override
    public Operador buscarPorLogin(String login) {
        Operador operadorEncontrado = repository.findByLoginEquals(login)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());
        return operadorEncontrado;
    }

    @Override
    public Optional<Operador> buscarPorLoginOptional(String login) {
        return repository.findByLoginEquals(login);
    }

    @Override
    public List<Operador> buscar(OperadorFiltroDTO filtro, Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Operador> buscarPaginado(OperadorFiltroDTO filtro, Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Operador atualizar(UUID id, Operador operador) {
        Operador operadorEncontrado = buscarPorId(id);
        validator.validate(operador);
        operador.setId(id);
        Operador operadorAtualizado = repository.save(operador);
        operadorAtualizado.setPessoa(operadorEncontrado.getPessoa());
        return operadorAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

}
