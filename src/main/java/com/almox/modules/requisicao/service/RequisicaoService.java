package com.almox.modules.requisicao.service;

import com.almox.core.exceptions.ApplicationRuntimeException;
import com.almox.core.exceptions.EntidadeNaoEncontradaException;
import com.almox.modules.produto.service.IProdutoService;
import com.almox.modules.requisicao.repository.ItemRequisicaoRepository;
import com.almox.modules.requisicao.repository.RequisicaoRepository;
import com.almox.modules.requisicao.model.ItemRequisicao;
import com.almox.modules.requisicao.model.Requisicao;
import com.almox.modules.requisicao.model.StatusRequisicao;
import com.almox.modules.usuario.UsuarioService;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequisicaoService implements IRequisicaoService {

    private final RequisicaoRepository requisicaoRepository;
    private final ItemRequisicaoRepository itemRequisicaoRepository;
    private final IProdutoService produtoService;
    private final UsuarioService usuarioService;

    public RequisicaoService(RequisicaoRepository requisicaoRepository, ItemRequisicaoRepository itemRequisicaoRepository, IProdutoService produtoService, UsuarioService usuarioService) {
        this.requisicaoRepository = requisicaoRepository;
        this.itemRequisicaoRepository = itemRequisicaoRepository;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public Requisicao criar(Requisicao entidade) {
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

        return requisicaoRepository.save(entidade);
    }
}
