package org.almox.modules.pessoa.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.config.validation.ValidatorAutoThrow;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.pessoa.dto.PessoaFiltroDTO;
import org.almox.modules.pessoa.model.Pessoa;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.almox.modules.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService implements IPessoaService {

    private final PessoaRepository repository;
    private final ValidatorAutoThrow validator;

    @Override
    @Transactional
    public <P extends Pessoa> P criar(@Valid P pessoa) {
        validator.validate(pessoa);
        buscarPorEmailOptional(pessoa.getEmail()).ifPresent(pessoaComMesmoEmail -> {
            throw new RegraNegocioException("${email_ja_cadastrado}");
        });
        if (pessoa instanceof PessoaJuridica) {
            repository.buscarPorCnpj(((PessoaJuridica) pessoa).getCnpj()).ifPresent(pessoaComMesmoCnpj -> {
                throw new RegraNegocioException("${cnpj_ja_cadastrado}");
            });
        } else if (pessoa instanceof PessoaFisica) {
            repository.buscarPorCpf(((PessoaFisica) pessoa).getCpf()).ifPresent(pessoaComMesmoCpf -> {
                throw new RegraNegocioException("${cpf_ja_cadastrado}");
            });
        }

        return repository.save(pessoa);
    }

    @Override
    public Pessoa buscarPorId(UUID id) {
        return buscarPorIdOptional(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());
    }

    @Override
    public Optional<Pessoa> buscarPorIdOptional(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Pessoa buscarPorEmail(String email) {
        return buscarPorEmailOptional(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());
    }

    private Optional<Pessoa> buscarPorEmailOptional(String email) {
        return repository.findByEmailEquals(email);
    }

    @Override
    public List<Pessoa> buscar(PessoaFiltroDTO filtro, Sort sort) {
        return filtro.tipo == null
                ? repository.findAll(filtro.nome, filtro.email, sort)
                : repository.findAll(filtro.nome, filtro.email, filtro.tipo, sort);
    }

    @Override
    public Page<Pessoa> buscarPaginado(PessoaFiltroDTO filtro, Pageable pageable) {
        return filtro.tipo == null
                ? repository.findAll(filtro.nome, filtro.email, pageable)
                : repository.findAll(filtro.nome, filtro.email, filtro.tipo.name(), pageable);
    }

    @Override
    @Transactional
    public <P extends Pessoa> P atualizar(UUID id, P pessoa) {
        buscarPorId(id);
        validator.validate(pessoa);
        pessoa.setId(id);
        return repository.save(pessoa);
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
