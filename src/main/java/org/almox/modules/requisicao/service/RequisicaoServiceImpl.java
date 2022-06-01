package org.almox.modules.requisicao.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.ApplicationRuntimeException;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.core.exceptions.RegraNegocioException;
import org.almox.core.exceptions.UnauthorizedException;
import org.almox.modules.departamento.service.DepartamentoService;
import org.almox.modules.movimento.MovimentoService;
import org.almox.modules.operador.dto.ContextoOperador;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.service.OperadorService;
import org.almox.modules.produto.service.ProdutoService;
import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.model.ItemRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.almox.modules.requisicao.repository.ItemRequisicaoRepository;
import org.almox.modules.requisicao.repository.RequisicaoRepository;
import org.almox.modules.util.GeradorCodigoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequisicaoServiceImpl implements RequisicaoService {

    private final ContextoOperador contextoOperador;
    private final Validator validator;
    private final RequisicaoRepository requisicaoRepository;
    private final ItemRequisicaoRepository itemRequisicaoRepository;
    private final OperadorService operadorService;
    private final DepartamentoService departamentoService;
    private final ProdutoService produtoService;
    private final MovimentoService movimentoService;

    @Override
    @Transactional
    public Requisicao criar(Requisicao requisicao) {
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        // Inserir valores padrões da Requisição
        requisicao.setRequisitante(operadorLogado);
        requisicao.setDataRequisicao(LocalDateTime.now());
        requisicao.setStatus(StatusRequisicao.AGUARDANDO_ATENDIMENTO);

        validator.validate(requisicao);
        requisicao.setAlmoxarife(operadorService.buscarPorId(requisicao.getAlmoxarife().getId()));
        requisicao.setDepartamento(departamentoService.buscarPorId(requisicao.getDepartamento().getId()));

        // Salvar a requisição
        Requisicao requisicaoSalva = requisicaoRepository.save(requisicao);

        // Validar cada item da requisição e salvá-los
        Set<ItemRequisicao> itens = salvarItens(requisicao.getItens(), requisicao);

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
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);
        String status = filtro.status == null ? null : filtro.status.name();
        return requisicaoRepository.buscar(status, operadorLogado.getId(), paginacao);
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

        if (!StatusRequisicao.AGUARDANDO_ATENDIMENTO.equals(requisicaoParaIniciarAtendimento.getStatus()))
            throw new RegraNegocioException("Não é possível iniciar o atendimento em uma requisição que não esteja aguardando atendimento");
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

    @Transactional
    @Override
    public String entregarRequisicao(UUID id) {
        Requisicao requisicaoEncontrada = buscarPorId(id);
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        if (StatusRequisicao.CANCELADA.equals(requisicaoEncontrada.getStatus()))
            throw new RegraNegocioException("Não é possível entregar uma requisição que já foi cancelada");
        if (!operadorLogado.equals(requisicaoEncontrada.getAlmoxarife()))
            throw new RegraNegocioException("Apenas o Almoxarife responsável pode entregar os produtos de uma requisição");

        requisicaoEncontrada.setStatus(StatusRequisicao.AGUARDANDO_RECEBIMENTO);
        requisicaoEncontrada.setCodigoConfirmacao(GeradorCodigoUtil.gerarCodigoAleatorio());
        requisicaoEncontrada = requisicaoRepository.save(requisicaoEncontrada);
        return requisicaoEncontrada.getCodigoConfirmacao();
    }

    @Transactional
    @Override
    public void confirmarRecebimento(String codigoConfirmacao) {
        Requisicao requisicaoEncontrada = requisicaoRepository.buscarPorCodigoConfirmacao(codigoConfirmacao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("${requisicao_nao_encontrada}"));
        Operador operadorLogado = contextoOperador.getOperadorLogado().orElseThrow(UnauthorizedException::new);

        if (!StatusRequisicao.AGUARDANDO_RECEBIMENTO.equals(requisicaoEncontrada.getStatus()))
            throw new RegraNegocioException("Não é possível confirmar uma requisição que não esteja aguardando recebimento");
        if (!operadorLogado.equals(requisicaoEncontrada.getRequisitante()))
            throw new RegraNegocioException("Apenas o Requisitante pode confirmar o recebimento de uma requisição");

        requisicaoEncontrada.setStatus(StatusRequisicao.ENTREGUE);
        requisicaoRepository.save(requisicaoEncontrada);
        movimentoService.movimentarProdutosRequisicao(requisicaoEncontrada);
    }

    @Transactional
    @Override
    public Requisicao alterarItens(UUID id, Set<ItemRequisicao> novosItens) {
        Requisicao requisicaoEncontrada = buscarPorId(id);
        if (!StatusRequisicao.statusPermissivosAlteracao().contains(requisicaoEncontrada.getStatus())) {
            throw new RegraNegocioException("${requisicao_nao_permite_alteracao}");
        }
        itemRequisicaoRepository.deleteAllByIdInBatch(requisicaoEncontrada.getItens()
                .stream()
                .map(ItemRequisicao::getId)
                .collect(Collectors.toSet())
        );
        Set<ItemRequisicao> novosItensSalvos = salvarItens(novosItens, requisicaoEncontrada);
        requisicaoEncontrada.setItens(novosItensSalvos);
        return requisicaoRepository.save(requisicaoEncontrada);
    }

    private Set<ItemRequisicao> salvarItens(Set<ItemRequisicao> itens, Requisicao requisicaoPertencente) {
        Set<String> erros = new HashSet<>();
        Set<ItemRequisicao> itensSalvos = normalizarItensRequisicao(itens)
                .stream()
                .map(amostraItem -> {
                    ItemRequisicao item = new ItemRequisicao();
                    item.setQuantidade(amostraItem.getQuantidade());
                    try {
                        item.setProduto(produtoService.buscarPorId(amostraItem.getProduto().getId()));
                        item.setRequisicao(requisicaoPertencente);
                        return itemRequisicaoRepository.save(item);
                    } catch (EntidadeNaoEncontradaException e) {
                        erros.add("Produto não encontrado. ID: " + amostraItem.getProduto().getId());
                        return null;
                    }
                })
                .collect(Collectors.toSet());

        if (!erros.isEmpty()) {
            throw new ApplicationRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, erros.toArray(new String[0]));
        }
        return itensSalvos;
    }

    /**
     * Este método normaliza um {@link Set<ItemRequisicao>} removendo itensParaNormalizar com produtos repetidos e centralizando a quanntidade repetida em apenas um item
     *
     * @param itensParaNormalizar a serem normalizados
     * @return itensParaNormalizar normalizados
     */
    private Set<ItemRequisicao> normalizarItensRequisicao(Set<ItemRequisicao> itensParaNormalizar) {
        List<ItemRequisicao> itensList = new ArrayList<>(itensParaNormalizar);

        final Set<Integer> indexParaRemover = new HashSet<>();
        for (int i = 0; i < itensList.size(); i++) {
            if (indexParaRemover.contains(i)) { // se já estiver marcado para remoção significa que era um item repetido, então o ignora
                continue;
            }
            ItemRequisicao itemOriginal = itensList.get(i);
            for (int j = 0; j < itensList.size(); j++) {
                boolean isMesmoProduto = itensList.get(j).getProduto().getId().equals(itemOriginal.getProduto().getId());
                boolean isMesmoItem = i == j;
                if (isMesmoProduto && !isMesmoItem) {
                    ItemRequisicao itemRepetido = itensList.get(j);
                    itemOriginal.adicionarQuantidade(itemRepetido.getQuantidade());
                    indexParaRemover.add(j);
                }
            }
        }

        ItemRequisicao[] arrayItens = itensParaNormalizar.toArray(new ItemRequisicao[0]);
        for (int index : indexParaRemover)
            arrayItens[index] = null;

        return Arrays.stream(arrayItens)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
