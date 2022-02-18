import { DialogService } from 'primeng/dynamicdialog';
import { Mensagens } from 'src/utils/Mensagens';
import { MessageService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Requisicao } from 'src/model/requisicao';

import { ItemRequisicao } from '../../../../model/item-requisicao';
import { RequisicaoService } from '../../services/requisicao.service';
import { ModalHistoricoComponent } from 'src/modules/produto/components/modal-historico/modal-historico-produto.component';

@Component({
    selector: "requisicao-atendimento",
    templateUrl: "./requisicao-atendimento.component.html",
    styleUrls: ["./requisicao-atendimento.component.scss"],
})
export class RequisicaoAtendimentoComponent implements OnInit {
    NOME_PAGINA = "Informações da Requisição";
    registro: Requisicao = { status: {}};
    registroNoFormulario: any;

    constructor(
        private requisicaoService: RequisicaoService,
        private messageService : MessageService,
        private activatedRoute: ActivatedRoute,
        private router : Router,
        private dialogService: DialogService
    ) {}

    ngOnInit(): void {
        this.activatedRoute.params.subscribe(params => {
            const id: number = params["id"];
            if (id) {
                this.requisicaoService
                    .buscarPorId(id)
                    .subscribe(registroEncontrado => {
                        this.registro = registroEncontrado;
                    });
            }
        });
    }

    onSubmit(formulario: NgForm): void {}

    onVisualizarProduto(item: ItemRequisicao) {
        this.router.navigate([`/produtos/editar/${item.produto.id}`])
    }

    onVisualizarHistoricoMovimento(item : ItemRequisicao) {
        this.dialogService.open(ModalHistoricoComponent, {
            header: "Histórico de Estoque",
            width: "70%",
            data: item.produto
        });
    }

    onEditarItem({ rowIndex, item }) {
        this.registroNoFormulario = { rowIndex, item: Object.assign({}, item) };
    }

    onExcluirItem({ rowIndex }) {
        this.registro.itens.splice(rowIndex, 1);
    }

    onEditarItemSubmit(item: ItemRequisicao) {
        if (this.registroNoFormulario.rowIndex !== -1) {
            this.registro.itens[this.registroNoFormulario.rowIndex] =
                Object.assign({}, item);
        } else {
            this.registro.itens.push(Object.assign({}, item));
        }
        this.registroNoFormulario = null;
    }

    onAtender() {
        this.requisicaoService
            .atenderRequisicao(this.registro.id)
            .subscribe(() => {
                this.ngOnInit();
                this.messageService.add(Mensagens.INFO_REQUISICAO_EM_ATENDIMENTO);
            });
    }

    onCancelar() {
        this.requisicaoService
            .cancelarRequisicao(this.registro.id)
            .subscribe(() => {
                this.ngOnInit();
            });
    }

    onEntregar() {
        this.requisicaoService
            .entregarRequisicao(this.registro)
            .subscribe(() => {
                this.ngOnInit();
                this.messageService.add(Mensagens.SUCESSO_REQUISICAO_ENTREGUE);
            });
    }
}
