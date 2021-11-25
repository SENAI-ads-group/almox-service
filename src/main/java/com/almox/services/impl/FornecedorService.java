package com.almox.services.impl;
<<<<<<< HEAD
import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import com.almox.model.entidades.Usuario;
=======

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
>>>>>>> d06b990... Interligação de funcionalidades
import com.almox.repositories.ContatoRepository;
import com.almox.repositories.FornecedorRepository;
import com.almox.services.IFornecedorService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
=======

>>>>>>> d06b990... Interligação de funcionalidades
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FornecedorService implements IFornecedorService {
<<<<<<< HEAD

    private final FornecedorRepository fornecedorRepository;
    private final ContatoRepository contatoRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, ContatoRepository contatoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.contatoRepository = contatoRepository;
=======
    private final FornecedorRepository fornecedorRepository;
    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, ContatoRepository contatoRepository, UsuarioService usuarioService) {
        this.fornecedorRepository = fornecedorRepository;
        this.contatoRepository = contatoRepository;
        this.usuarioService = usuarioService;
>>>>>>> d06b990... Interligação de funcionalidades
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
<<<<<<< HEAD
        entidade.setContato(contatoRepository.save(entidade.getContato()));
=======
       entidade.setContato(contatoRepository.save(entidade.getContato()));
>>>>>>> d06b990... Interligação de funcionalidades
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
<<<<<<< HEAD
        var fornecedorEcontrado = buscarPorId(id);
        fornecedorEcontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        fornecedorEcontrado.setExcluidoPor(usuarioExcluidor);
        fornecedorRepository.save(fornecedorEcontrado);
    }
=======
        var entidadeEncontrada = buscarPorId(id);
        entidadeEncontrada.setDataExclusao(LocalDateTime.now());
        entidadeEncontrada.setExcluidoPor(usuarioService.getUsuarioLogado());
        fornecedorRepository.save(entidadeEncontrada);
    }

>>>>>>> d06b990... Interligação de funcionalidades
}
