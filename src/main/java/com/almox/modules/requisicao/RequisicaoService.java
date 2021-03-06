package com.almox.modules.requisicao;

import com.almox.core.exceptions.ApplicationRuntimeException;
import com.almox.core.exceptions.EntidadeNaoEncontradaException;
import com.almox.core.exceptions.RegraNegocioException;
import com.almox.modules.movimento.MovimentoService;
import com.almox.modules.produto.ProdutoService;
import com.almox.modules.crud.CrudService;
import com.almox.modules.requisicao.model.FiltroRequisicaoDTO;
import com.almox.modules.requisicao.model.ItemRequisicao;
import com.almox.modules.requisicao.model.Requisicao;
import com.almox.modules.requisicao.model.StatusRequisicao;
import com.almox.modules.requisicao.repository.ItemRequisicaoRepository;
import com.almox.modules.requisicao.repository.RequisicaoRepository;
import com.almox.modules.usuario.UsuarioService;
import com.almox.modules.util.CondicaoUtil;
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
        // Inserir valores padr??es da Requisi????o
        entidade.setRequisitante(usuarioService.getUsuarioLogado());
        entidade.setStatus(StatusRequisicao.AGUARDANDO_ATENDIMENTO);

        // Salvar a requisi????o
        var entidadeSalva = salvar(entidade);

        // Validar cada item da requisi????o e salv??-los
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
                        erros.add("Produto n??o encontrado. ID: " + amostraItem.getProduto().getId());
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
        return repository.findAll(filtro);
    }

    @Override
    protected Requisicao _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    public void atenderRequisicao(Long id) {
        var requisicao = buscarPorId(id);
        if (StatusRequisicao.CANCELADA.equals(requisicao.getStatus()))
            throw new RegraNegocioException("N??o ?? poss??vel iniciar o atendimento em uma requisi????o que j?? foi cancelada");
        if(!usuarioService.getUsuarioLogado().equals(requisicao.getAlmoxarife()))
            throw new RegraNegocioException("Apenas o Almoxarife respons??vel pode atender uma requisi????o");

        requisicao.setStatus(StatusRequisicao.EM_ATENDIMENTO);
        repository.save(requisicao);
    }

    public void cancelarRequisicao(Long id) {
        var requisicao = buscarPorId(id);
        if (StatusRequisicao.ENTREGUE.equals(requisicao.getStatus()))
            throw new RegraNegocioException("N??o ?? poss??vel cancelar uma requisi????o que j?? foi entregue");

        requisicao.setStatus(StatusRequisicao.CANCELADA);
        repository.save(requisicao);
    }

    public void entregarRequisicao(Long id, Requisicao requisicaoEntregue) {
        var requisicaoEncontradda = buscarPorId(id);
        if (StatusRequisicao.CANCELADA.equals(requisicaoEncontradda.getStatus()))
            throw new RegraNegocioException("N??o ?? poss??vel entregar uma requisi????o que j?? foi cancelada");
        if (!usuarioService.getUsuarioLogado().equals(requisicaoEncontradda.getAlmoxarife()))
            throw new RegraNegocioException("Apenas o Almoxarife respons??vel pode entregar os produtos de uma requisi????o");

        requisicaoEncontradda.setStatus(StatusRequisicao.ENTREGUE);
        requisicaoEncontradda = repository.save(requisicaoEncontradda);
        movimentoService.movimentarProdutosRequisicao(requisicaoEncontradda);
    }

    /**
     * Este m??todo normaliza um {@link Set<ItemRequisicao>} removendo itens com produtos repetidos e centralizando a quanntidade repetida em apenas um item
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
            if (indexParaRemover.contains(i)) { // se j?? estiver marcado para remo????o significa que era um item repetido, ent??o o ignora
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