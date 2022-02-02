package com.almox.modules.fornecedor.service;

import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;
import com.almox.modules.fornecedor.repository.FornecedorRepository;
import com.almox.modules.pessoa.ContatoRepository;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FornecedorService implements IFornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, ContatoRepository contatoRepository, UsuarioService usuarioService) {
        this.fornecedorRepository = fornecedorRepository;
        this.contatoRepository = contatoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Fornecedor> buscarTodos(FiltroFornecedorDTO filtro) {
        return fornecedorRepository.findAll(filtro);
    }

    @Override
    public List<Fornecedor> buscarTodos() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Fornecedor buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(fornecedorRepository.findById(id));
    }

    @Transactional
    public Fornecedor criar(@Valid Fornecedor fornecedor) {
        return salvar(fornecedor);

    }

    private Fornecedor salvar(Fornecedor entidade) {
        entidade.setContato(contatoRepository.save(entidade.getContato()));
        return fornecedorRepository.save(entidade);
    }

    @Override
    @Transactional
    public Fornecedor atualizar(Long id, @Valid Fornecedor entidade) {
        buscarPorId(id);
        return salvar(entidade);
    }

    @Override
    public void excluir(long id) {
        var entidadeEncontrada = buscarPorId(id);
        entidadeEncontrada.setDataExclusao(LocalDateTime.now());
        entidadeEncontrada.setExcluidoPor(usuarioService.getUsuarioLogado());
        fornecedorRepository.save(entidadeEncontrada);
    }
}
