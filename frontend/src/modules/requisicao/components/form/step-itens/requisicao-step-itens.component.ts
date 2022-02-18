import { Mensagens } from "src/utils/Mensagens";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Message, MessageService } from "primeng/api";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { ProdutoService } from "src/modules/produto/services/produto.service";

import { ItemRequisicao } from "../../../../../model/item-requisicao";
import { RequisicaoStepMergeService } from "../../../services/requisicao-step-merge.service";

@Component({
    selector: "requisicao-step-itens",
    templateUrl: "./requisicao-step-itens.component.html",
    styleUrls: ["./requisicao-step-itens.component.scss"],
})
export class RequisicaoStepItensComponent implements OnInit {
    registroNoFormulario: any;
    indexExclusao: number;

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        public stepMergeService: RequisicaoStepMergeService,
        private dialogService: DialogService,
        private messageService: MessageService,
        private produtoService: ProdutoService
    ) {}

    dialogItemRequisicao: DynamicDialogRef;

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

    onFormularioSubmit(itemFormulario: ItemRequisicao) {
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
