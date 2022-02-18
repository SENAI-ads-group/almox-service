import { Mensagens } from "src/app/utils/Mensagens";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Message, MessageService } from "primeng/api";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { ProdutoService } from "src/app/modules/produto/services/produto.service";

import { ItemPedido } from "../../../../../model/item-pedido";
import { PedidoStepMergeService } from "../../../services/pedido-step-merge.service";

@Component({
    selector: "pedido-step-itens",
    templateUrl: "./pedido-step-itens.component.html",
    styleUrls: ["./pedido-step-itens.component.scss"],
})
export class PedidoStepItensComponent implements OnInit {
    registroNoFormulario: any;
    indexExclusao: number;

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        public stepMergeService: PedidoStepMergeService,
        private dialogService: DialogService,
        private messageService: MessageService,
        private produtoService: ProdutoService
    ) {}

    dialogItemPedido: DynamicDialogRef;

    ngOnInit(): void {}

    onEditar({ rowIndex, item }) {
        this.registroNoFormulario = { rowIndex, item: Object.assign({}, item) };
    }

    onExcluir({ rowIndex }) {
        this.messageService.clear('confirmacao')
        this.messageService.add({
            key: "confirmacao",
            sticky: true,
            severity: "warn",
            summary: "Deseja excluir este item?",
            detail: "Confirme para prosseguir",
        });
        this.indexExclusao = rowIndex;
    }

    onFormularioSubmit(itemFormulario: ItemPedido) {
        if (
            !itemFormulario ||
            !itemFormulario.produto ||
            !itemFormulario.quantidade
        ) {
            return;
        }

        let mensagem: Message;
        if (this.registroNoFormulario.rowIndex !== -1) {
            this.stepMergeService.state.itens[
                this.registroNoFormulario.rowIndex
            ] = Object.assign({}, itemFormulario);
            mensagem = Mensagens.SUCESSO_PRODUTO_ATUALIZADO;
        } else {
            this.stepMergeService.state.itens.push(
                Object.assign({}, itemFormulario)
            );
            mensagem = Mensagens.SUCESSO_PRODUTO_ADICIONADO;
        }

        this.messageService.add(mensagem);
        this.registroNoFormulario = null; // fecha o formulário
    }

    onFinalizar() {

    }

    onConfirmarExclusao() {
        this.stepMergeService.state.itens.splice(this.indexExclusao, 1);
        this.messageService.clear('confirmacao')
    }

    onRejeitarExclusao() {
        this.messageService.clear('confirmacao')
        this.indexExclusao = -1;
    }
}
