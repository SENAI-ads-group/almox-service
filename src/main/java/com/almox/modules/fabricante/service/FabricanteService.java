package com.almox.modules.fabricante.service;

import com.almox.modules.fabricante.model.Fabricante;
import com.almox.modules.fabricante.model.FiltroFabricanteDTO;
import com.almox.modules.fabricante.repository.FabricanteRepository;
import com.almox.modules.usuario.model.Usuario;
import com.almox.modules.pessoa.ContatoRepository;
import com.almox.modules.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FabricanteService implements IFabricanteService {
    private final FabricanteRepository fabricanteRepository;
    private final ContatoRepository contatoRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository, ContatoRepository contatoRepository) {
        this.fabricanteRepository = fabricanteRepository;
        this.contatoRepository = contatoRepository;
    }

    @Override
    public List<Fabricante> buscarTodos(FiltroFabricanteDTO filtro) {
        return fabricanteRepository.findAll(filtro);
    }

    @Override
    public List<Fabricante> buscarTodos() {
        return fabricanteRepository.findAll();
    }

    @Override
    public Fabricante buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(fabricanteRepository.findById(id));
    }

    @Transactional
    public Fabricante criar(@Valid Fabricante fabricante) {
        return salvar(fabricante);

    }

    private Fabricante salvar(Fabricante entidade) {
       entidade.setContato(contatoRepository.save(entidade.getContato()));
        return fabricanteRepository.save(entidade);
    }

    @Override
    @Transactional
    public Fabricante atualizar(Long id, @Valid Fabricante entidade) {
        buscarPorId(id);
        return salvar(entidade);
    }

    @Override
    public void excluir(long id) {
        var fabricanteEcontrado = buscarPorId(id);
        fabricanteEcontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        fabricanteEcontrado.setExcluidoPor(usuarioExcluidor);
        fabricanteRepository.save(fabricanteEcontrado);
    }


}
