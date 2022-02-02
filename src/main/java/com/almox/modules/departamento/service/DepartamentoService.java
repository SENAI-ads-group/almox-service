package com.almox.modules.departamento.service;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
import com.almox.modules.departamento.repository.DepartamentoRepository;
import com.almox.modules.usuario.model.Usuario;
import com.almox.modules.orcamento.repository.OrcamentoDepartamentoRepository;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final OrcamentoDepartamentoRepository orcamentoDepartamentoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository, OrcamentoDepartamentoRepository orcamentoDepartamentoRepository, UsuarioService usuarioService) {
        this.departamentoRepository = departamentoRepository;
        this.orcamentoDepartamentoRepository = orcamentoDepartamentoRepository;
        this.usuarioService = usuarioService;
    }

    public List<Departamento> buscarTodos(FiltroDepartamentoDTO filtro) {
        return departamentoRepository.findAll(filtro);
    }

    @Override
    public List<Departamento> buscarTodos() {
        return departamentoRepository.findAll();
    }

    public Departamento buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(departamentoRepository.findById(id));
    }

    public Departamento criar(@Valid Departamento entidade) {
        return salvar(entidade);
    }

    @Transactional
    public Departamento atualizar(Long id, Departamento entidade) {
        buscarPorId(id);
        return salvar(entidade);
    }

    private Departamento salvar(Departamento entidade) {
        return departamentoRepository.save(entidade);
    }

    @Override
    public void excluir(long id) {
        var entidadeEncontrada = buscarPorId(id);
        entidadeEncontrada.setDataExclusao(LocalDateTime.now());
        entidadeEncontrada.setExcluidoPor(usuarioService.getUsuarioLogado());
        departamentoRepository.save(entidadeEncontrada);
    }

    @Override
    public List<Departamento> buscarAssociadosUsuario(Usuario usuarioLogado) {
        return departamentoRepository.findAllByUsuariosContaining(usuarioLogado);
    }
}
