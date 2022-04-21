package com.almox.modules.departamento;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
import com.almox.modules.departamento.repository.DepartamentoRepository;
import com.almox.modules.departamento.repository.OrcamentoDepartamentoRepository;
import com.almox.modules.usuario.model.UsuarioDTO;
import com.almox.modules.produto.ProdutoService;
import com.almox.modules.crud.CrudService;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoService extends CrudService<Departamento, FiltroDepartamentoDTO> {

    @Getter
    private final DepartamentoRepository repository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final OrcamentoDepartamentoRepository orcamentoDepartamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository repository, UsuarioService usuarioService, ProdutoService produtoService, OrcamentoDepartamentoRepository orcamentoDepartamentoRepository) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
        this.orcamentoDepartamentoRepository = orcamentoDepartamentoRepository;
    }

    protected List<Departamento> _buscarTodos(FiltroDepartamentoDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    protected List<Departamento> _buscarTodos() {
        return repository.findAll();
    }

    public Departamento _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    protected Departamento _criar(@Valid Departamento entidade) {
        return salvar(entidade);
    }

    @Transactional
    protected Departamento _atualizar(Long id, Departamento entidade) {
        this.buscarPorId(id);
        return salvar(entidade);
    }

    private Departamento salvar(Departamento entidade) {
        return repository.save(entidade);
    }

    public List<Departamento> buscarAssociadosUsuarioLogado() {
        return buscarAssociadosUsuario(usuarioService.getUsuarioLogado().getId());
    }

    public List<Departamento> buscarAssociadosUsuario(String idUsuario) {

        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(idUsuario);
        return _buscarTodos()
                .stream()
                .filter(dpt -> {
                    return dpt.getUsuarios()
                            .stream()
                            .map(UsuarioDTO::getId).anyMatch(id -> id.equals(idUsuario));
                })
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Departamento> buscarPorRelacaoComProduto(Long idProduto, boolean relacionados) {
        var produto = produtoService.buscarPorId(idProduto);
        return (relacionados
                ? repository.findAllByProduct(produto.getId())
                : repository.findAllByNotProduct(produto.getId())
        ).stream().distinct().collect(Collectors.toList());
    }
}
