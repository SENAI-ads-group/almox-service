package org.almox.modules.fornecedor.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.modules.fornecedor.dto.FiltroFornecedor;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.fornecedor.repository.FornecedorRepository;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.almox.modules.pessoa.service.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorServiceImpl implements FornecedorService {

    private final Validator validator;
    private final FornecedorRepository fornecedorRepository;
    private final PessoaServiceImpl pessoaService;

    @Override
    public Fornecedor criar(Fornecedor fornecedor) {
        try {
            PessoaJuridica pessoaAssociadaAoFornecedor = fornecedor.getPessoa().isNew()
                    ? pessoaService.criar(fornecedor.getPessoa())
                    : (PessoaJuridica) pessoaService.buscarPorId(fornecedor.getPessoa().getId());
            fornecedor.setPessoa(pessoaAssociadaAoFornecedor);
        } catch (ClassCastException e) {
            throw new RegraNegocioException("${fornecedor_pessoa_deve_ser_juridica}");
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException("${fornecedor_pessoa_deve_nao_encontrada}");
        }
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public Fornecedor buscarPorId(UUID id) {
        Fornecedor fornecedorEncontrado = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${fornecedor_nao_encontrado}"));
        return fornecedorEncontrado;
    }

    @Override
    public Page<Fornecedor> buscar(FiltroFornecedor filtro, Pageable pageable) {
        return fornecedorRepository.buscarAtivosPorCpjOuNome(filtro.cnpj, filtro.nome, pageable);
    }

    @Override
    public Page<Fornecedor> buscarExcluidos(FiltroFornecedor filtro, Pageable pageable) {
        return fornecedorRepository.buscarExcluidosPorCpjOuNome(filtro.cnpj, filtro.nome, pageable);
    }

    @Override
    public Fornecedor atualizar(UUID id, Fornecedor fornecedor) {
        Fornecedor fornecedorEncontrado = buscarPorId(id);
        validator.validate(fornecedor);
        fornecedor.setId(id);
        Fornecedor fornecedorAtualizado = fornecedorRepository.save(fornecedor);
        fornecedorAtualizado.setPessoa(fornecedorEncontrado.getPessoa());
        return fornecedorAtualizado;
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        fornecedorRepository.deleteById(id);
    }
}
