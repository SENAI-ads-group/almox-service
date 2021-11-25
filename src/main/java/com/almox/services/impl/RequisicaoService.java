package com.almox.services.impl;

import com.almox.exceptions.ApplicationRuntimeException;
import com.almox.exceptions.EntidadeNaoEncontradaException;
import com.almox.model.entidades.ItemRequisicao;
import com.almox.model.entidades.Requisicao;
import com.almox.model.enums.StatusRequisicao;
import com.almox.repositories.ItemRequisicaoRepository;
import com.almox.repositories.RequisicaoRepository;
import com.almox.services.IProdutoService;
import com.almox.services.IRequisicaoService;
import com.almox.util.ColecaoUtil;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
