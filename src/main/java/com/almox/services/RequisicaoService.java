package com.almox.services;

import com.almox.exceptions.ApplicationRuntimeException;
import com.almox.exceptions.EntidadeNaoEncontradaException;
import com.almox.model.dto.FiltroRequisicaoDTO;
import com.almox.model.entidades.ItemRequisicao;
import com.almox.model.entidades.Requisicao;
import com.almox.model.enums.StatusRequisicao;
import com.almox.repositories.requisicao.ItemRequisicaoRepository;
import com.almox.repositories.requisicao.RequisicaoRepository;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequisicaoService extends CrudService<Requisicao, FiltroRequisicaoDTO>  {

    @Getter
    private final RequisicaoRepository repository;
    private final ItemRequisicaoRepository itemRequisicaoRepository;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    public RequisicaoService(RequisicaoRepository repository, ItemRequisicaoRepository itemRequisicaoRepository, ProdutoService produtoService, UsuarioService usuarioService) {
        this.repository = repository;
        this.itemRequisicaoRepository = itemRequisicaoRepository;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected Requisicao _criar(Requisicao entidade) {
        List<String> erros = Lists.newArrayList();
        var itens = entidade.getItens().stream()
                .map(amostraItem -> {
                    ItemRequisicao item = new ItemRequisicao();
                    item.setQuantidade(amostraItem.getQuantidade());
                    try {
                        item.setProduto(produtoService.buscarPorId(amostraItem.getProduto().getId()));
                    } catch (EntidadeNaoEncontradaException e) {
                        erros.add("Produto não encontrado. ID: " + amostraItem.getProduto().getId());
                        return null;
                    }
                    return itemRequisicaoRepository.save(item);
                })
                .collect(Collectors.toList());
        entidade.setItens(itens);
        entidade.setRequisitante(usuarioService.getUsuarioLogado());
        entidade.setStatus(StatusRequisicao.ABERTO);

        if (!erros.isEmpty()) {
            throw new ApplicationRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, erros.toArray(new String[0]));
        }

        return salvar(entidade);
    }

    @Override
    protected Requisicao _atualizar(Long id, Requisicao entidade) {
        buscarPorId(id);
        entidade.setId(id);
        return salvar(entidade);
    }

    private Requisicao salvar(Requisicao entidade) {
        return repository.save(entidade);
    }

    @Override
    protected List<Requisicao> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    protected List<Requisicao> _buscarTodos(FiltroRequisicaoDTO filtro) {
        return repository.findAll();
    }
}
