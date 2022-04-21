package org.almox.modules.requisicao.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.ApplicationRuntimeException;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.core.exceptions.UnauthorizedException;
import org.almox.modules.movimento.MovimentoService;
import org.almox.modules.operador.dto.ContextoOperador;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.produto.service.ProdutoService;
import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.model.ItemRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.almox.modules.requisicao.repository.ItemRequisicaoRepository;
import org.almox.modules.requisicao.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequisicaoServiceImpl implements RequisicaoService {

    private final ContextoOperador contextoOperador;
    private final Validator validator;
    private final RequisicaoRepository requisicaoRepository;
    private final ItemRequisicaoRepository itemRequisicaoRepository;
    private final ProdutoService produtoService;
    private final MovimentoService movimentoService;

    @Override
    public Requisicao criar(Requisicao requisicao) {
        validator.validate(requisicao);
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        // Inserir valores padrões da Requisição
        requisicao.setRequisitante(operadorLogado);
        requisicao.setStatus(StatusRequisicao.AGUARDANDO_ATENDIMENTO);

        // Salvar a requisição
        var requisicaoSalva = requisicaoRepository.save(requisicao);

        // Validar cada item da requisição e salvá-los
        Set<String> erros = new HashSet<>();
        Set<ItemRequisicao> itens = normalizarItensRequisicao(requisicao.getItens())
                .stream()
                .map(amostraItem -> {
                    ItemRequisicao item = new ItemRequisicao();
                    item.setQuantidade(amostraItem.getQuantidade());
                    try {
                        item.setProduto(produtoService.buscarPorId(amostraItem.getProduto().getId()));
                        item.setRequisicao(requisicaoSalva);
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

        requisicaoSalva.setItens(itens);
        return requisicaoSalva;
    }

    @Override
    public Requisicao buscarPorId(UUID id) {
        Requisicao requisicaoEncontrada = requisicaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${requisicao_nao_encontrada}"));
        return requisicaoEncontrada;
    }

    @Override
    public Page<Requisicao> buscar(FiltroRequisicao filtro, Pageable paginacao) {
        return requisicaoRepository.buscar(filtro.status, paginacao);
    }

    @Override
    public void excluir(UUID id) {
        buscarPorId(id);
        requisicaoRepository.deleteById(id);
    }

    @Override
    public void atenderRequisicao(UUID id) {
        Requisicao requisicaoParaIniciarAtendimento = buscarPorId(id);
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        if (StatusRequisicao.CANCELADA.equals(requisicaoParaIniciarAtendimento.getStatus()))
            throw new RegraNegocioException("Não é possível iniciar o atendimento em uma requisição que já foi cancelada");
        if (!operadorLogado.equals(requisicaoParaIniciarAtendimento.getAlmoxarife()))
            throw new RegraNegocioException("Apenas o Almoxarife responsável pode atender uma requisição");

        requisicaoParaIniciarAtendimento.setStatus(StatusRequisicao.EM_ATENDIMENTO);
        requisicaoRepository.save(requisicaoParaIniciarAtendimento);
    }

    @Override
    public void cancelarRequisicao(UUID id) {
        Requisicao requisicaoParaCancelar = buscarPorId(id);
        if (StatusRequisicao.ENTREGUE.equals(requisicaoParaCancelar.getStatus()))
            throw new RegraNegocioException("Não é possível cancelar uma requisição que já foi entregue");

        requisicaoParaCancelar.setStatus(StatusRequisicao.CANCELADA);
        requisicaoRepository.save(requisicaoParaCancelar);
    }

    @Override
    public void entregarRequisicao(UUID id, Requisicao requisicaoEntregue) {
        Requisicao requisicaoEncontrada = buscarPorId(id);
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        if (StatusRequisicao.CANCELADA.equals(requisicaoEncontrada.getStatus()))
            throw new RegraNegocioException("Não é possível entregar uma requisição que já foi cancelada");
        if (!contextoOperador.equals(requisicaoEncontrada.getAlmoxarife()))
            throw new RegraNegocioException("Apenas o Almoxarife responsável pode entregar os produtos de uma requisição");

        requisicaoEncontrada.setStatus(StatusRequisicao.ENTREGUE);
        requisicaoEncontrada = requisicaoRepository.save(requisicaoEncontrada);
        movimentoService.movimentarProdutosRequisicao(requisicaoEncontrada);
    }

    /**
     * Este método normaliza um {@link Set<ItemRequisicao>} removendo itens com produtos repetidos e centralizando a quanntidade repetida em apenas um item
     *
     * @param itens a serem normalizados
     * @return itens normalizados
     */
    private Set<ItemRequisicao> normalizarItensRequisicao(Set<ItemRequisicao> itens) {
        List<ItemRequisicao> itensList = new ArrayList<>();
        BiPredicate<ItemRequisicao, ItemRequisicao> condicaoItemComMesmoProduto = (itemOriginal, itemComparacao) -> (
                itemComparacao.getProduto().equals(itemOriginal.getProduto()) && !itemOriginal.equals(itemComparacao)
        );

        final Set<Integer> indexParaRemover = new HashSet<>();
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
