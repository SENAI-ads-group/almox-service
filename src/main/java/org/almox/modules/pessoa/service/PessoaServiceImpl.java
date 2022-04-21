package org.almox.modules.pessoa.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.pessoa.dto.FiltroPessoa;
import org.almox.modules.pessoa.model.Pessoa;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.almox.modules.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaServiceImpl implements PessoaService {

    private final Validator validator;
    private final PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public <P extends Pessoa> P criar(@Valid P pessoa) {
        validator.validate(pessoa);
        buscarPorEmailOptional(pessoa.getEmail()).ifPresent(pessoaComMesmoEmail -> {
            throw new RegraNegocioException("${email_ja_cadastrado}");
        });
        if (pessoa instanceof PessoaJuridica) {
            pessoaRepository.buscarPorCnpj(((PessoaJuridica) pessoa).getCnpj()).ifPresent(pessoaComMesmoCnpj -> {
                throw new RegraNegocioException("${cnpj_ja_cadastrado}");
            });
        } else if (pessoa instanceof PessoaFisica) {
            pessoaRepository.buscarPorCpf(((PessoaFisica) pessoa).getCpf()).ifPresent(pessoaComMesmoCpf -> {
                throw new RegraNegocioException("${cpf_ja_cadastrado}");
            });
        }

        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa buscarPorId(UUID id) {
        return buscarPorIdOptional(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());
    }

    @Override
    public Optional<Pessoa> buscarPorIdOptional(UUID uuid) {
        return pessoaRepository.findById(uuid);
    }

    @Override
    public Pessoa buscarPorEmail(String email) {
        return buscarPorEmailOptional(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());
    }

    private Optional<Pessoa> buscarPorEmailOptional(String email) {
        return pessoaRepository.findByEmailEquals(email);
    }

    @Override
    public Page<Pessoa> buscar(FiltroPessoa filtro, Pageable paginacao) {
        return filtro.tipo == null
                ? pessoaRepository.buscar(filtro.nome, filtro.email, paginacao)
                : pessoaRepository.buscar(filtro.nome, filtro.email, filtro.tipo, paginacao);
    }

    @Override
    @Transactional
    public <P extends Pessoa> P atualizar(UUID id, P pessoa) {
        buscarPorId(id);
        validator.validate(pessoa);
        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        pessoaRepository.deleteById(id);
    }
}
