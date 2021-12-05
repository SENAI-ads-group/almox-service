package com.almox.services;

import com.almox.exceptions.ApplicationRuntimeException;
import com.almox.exceptions.EntidadeNaoEncontradaException;
import com.almox.exceptions.RegraNegocioException;
import com.almox.model.dto.FiltroRequisicaoDTO;
import com.almox.model.entidades.ItemRequisicao;
import com.almox.model.entidades.Requisicao;
import com.almox.model.enums.StatusRequisicao;
import com.almox.repositories.requisicao.ItemRequisicaoRepository;
import com.almox.repositories.requisicao.RequisicaoRepository;
import com.almox.util.CondicaoUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
public class RequisicaoService extends CrudService<Requisicao, FiltroRequisicaoDTO> {

    @Getter
    private final RequisicaoRepository repository;

    private final ItemRequisicaoRepository itemRequisicaoRepository;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final MovimentoService movimentoService;

    public RequisicaoService(RequisicaoRepository repository, ItemRequisicaoRepository itemRequisicaoRepository,
                             ProdutoService produtoService, UsuarioService usuarioService, MovimentoService movimentoService) {
        this.repository = repository;
        this.itemRequisicaoRepository = itemRequisicaoRepository;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.movimentoService = movimentoService;
    }

    @Override
    protected Requisicao _criar(Requisicao entidade) {
        // Inserir valores padrões da Requisição
        entidade.setRequisitante(usuarioService.getUsuarioLogado());
        entidade.setStatus(StatusRequisicao.AGUARDANDO_ATENDIMENTO);

        // Salvar a requisição
        var entidadeSalva = salvar(entidade);

        // Validar cada item da requisição e salvá-los
        List<String> erros = Lists.newArrayList();
        var itens = normalizarIntesRequisicao(entidade.getItens())
                .stream()
                .map(amostraItem -> {
                    ItemRequisicao item = new ItemRequisicao();
                    item.setQuantidade(amostraItem.getQuantidade());
                    try {
                        item.setProduto(produtoService.buscarPorId(amostraItem.getProduto().getId()));
                        item.setRequisicao(entidadeSalva);
                    } catch (EntidadeNaoEncontradaException e) {
                        erros.add("Produto não encontrado. ID: " + amostraItem.getProduto().getId());
                        return null;
                    }
                    return itemRequisicaoRepository.save(item);
                })
                .collect(Collectors.toSet());

        // Verificar se houve erros ao salvar algum dos itens
        if (!erros.isEmpty()) {
            throw new ApplicationRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, erros.toArray(new String[0]));
        }

        entidadeSalva.setItens(itens);
        return entidadeSalva;
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

    @Override
    protected Requisicao _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    public void atenderRequisicao(Long id) {
        var requisicao = buscarPorId(id);
        if (StatusRequisicao.CANCELADA.equals(requisicao.getStatus()))
            throw new RegraNegocioException("Não é possível iniciar o atendimento em uma requisição que já foi cancelada");

        requisicao.setStatus(StatusRequisicao.EM_ATENDIMENTO);
        repository.save(requisicao);
    }

    public void cancelarRequisicao(Long id) {
        var requisicao = buscarPorId(id);
        if (StatusRequisicao.ENTREGUE.equals(requisicao.getStatus()))
            throw new RegraNegocioException("Não é possível cancelar uma requisição que já foi entregue");

        requisicao.setStatus(StatusRequisicao.CANCELADA);
        repository.save(requisicao);
    }

    public void entregarRequisicao(Long id, Requisicao requisicaoEntregue) {
        var requisicaoEncontradda = buscarPorId(id);
        if (StatusRequisicao.CANCELADA.equals(requisicaoEncontradda.getStatus()))
            throw new RegraNegocioException("Não é possível entregar uma requisição que já foi cancelada");
        if (!usuarioService.getUsuarioLogado().equals(requisicaoEncontradda.getAlmoxarife()))
            throw new RegraNegocioException("Apenas o Almoxarife responsável pode entregar os produtos de uma requisição");

        requisicaoEncontradda.setStatus(StatusRequisicao.ENTREGUE);
        requisicaoEncontradda = repository.save(requisicaoEncontradda);
        movimentoService.movimentarProdutosRequisicao(requisicaoEncontradda);
    }

    /**
     * Este método normaliza um {@link Set<ItemRequisicao>} removendo itens com produtos repetidos e centralizando a quanntidade repetida em apenas um item
     *
     * @param itens a serem normalizados
     * @return itens normalizados
     */
    private Set<ItemRequisicao> normalizarIntesRequisicao(Set<ItemRequisicao> itens) {
        List<ItemRequisicao> itensList = Lists.newArrayList(itens);
        BiPredicate<ItemRequisicao, ItemRequisicao> condicaoItemComMesmoProduto = (itemOriginal, itemComparacao) -> (
                itemComparacao.getProduto().equals(itemOriginal.getProduto()) && !itemOriginal.equals(itemComparacao)
        );

        final Set<Integer> indexParaRemover = Sets.newHashSet();
        for (int i = 0; i < itensList.size(); i++) {
            if (indexParaRemover.contains(i)) { // se já estiver marcado para remoção significa que era um item repetido, então o ignora
                continue;
            }
            var itemOriginal = itensList.get(i);
            for (int j = 0; j < itensList.size(); j++) {
                if (condicaoItemComMesmoProduto.test(itemOriginal, itensList.get(j))) {
                    var itemRepetido = itensList.get(j);
                    itemOriginal.adicionarQuantidade(itemRepetido.getQuantidade());
                    indexParaRemover.add(j);
                }
            }
        }

        ItemRequisicao[] arrayItens = itens.toArray(new ItemRequisicao[0]);
        for (int index : indexParaRemover)
            arrayItens[index] = null;

        return Arrays.stream(arrayItens)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}