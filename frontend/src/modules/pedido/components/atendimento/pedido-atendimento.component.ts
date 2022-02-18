import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { MessageService } from "primeng/api";
import { DialogService } from "primeng/dynamicdialog";
import { Pedido } from "src/model/pedido";
import { ModalHistoricoComponent } from "src/modules/produto/components/modal-historico/modal-historico-produto.component";
import { Mensagens } from "src/utils/Mensagens";

import { ItemPedido } from "../../../../model/item-pedido";
import { PedidoService } from "../../services/pedido.service";

@Component({
    selector: "pedido-atendimento",
    templateUrl: "./pedido-atendimento.component.html",
    styleUrls: ["./pedido-atendimento.component.scss"],
})
export class PedidoAtendimentoComponent implements OnInit {
    NOME_PAGINA = "Informações da Requisição";
    registro: Pedido = { status: {} };
    registroNoFormulario: any;

    constructor(
        private pedidoService: PedidoService,
        private messageService: MessageService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private dialogService: DialogService
    ) {}

    ngOnInit(): void {
        this.activatedRoute.params.subscribe(params => {
            const id: number = params["id"];
            if (id) {
                this.pedidoService
                    .buscarPorId(id)
                    .subscribe(registroEncontrado => {
                        this.registro = registroEncontrado;
                    });
            }
        });
    }

    onSubmit(formulario: NgForm): void {}

    onVisualizarProduto(item: ItemPedido) {
        this.router.navigate([`/produtos/editar/${item.produto.id}`]);
    }

    onVisualizarHistoricoMovimento(item: ItemPedido) {
        this.dialogService.open(ModalHistoricoComponent, {
            header: "Histórico de Estoque",
            width: "70%",
            data: item.produto,
        });
    }

    onEditarItem({ rowIndex, item }) {
        this.registroNoFormulario = { rowIndex, item: Object.assign({}, item) };
    }

    onExcluirItem({ rowIndex }) {
        this.registro.itens.splice(rowIndex, 1);
    }

    onEditarItemSubmit(item: ItemPedido) {
        if (this.registroNoFormulario.rowIndex !== -1) {
            this.registro.itens[this.registroNoFormulario.rowIndex] =
                Object.assign({}, item);
        } else {
            this.registro.itens.push(Object.assign({}, item));
        }
        this.registroNoFormulario = null;
    }

    onReceber() {
        this.pedidoService.receberPedido(this.registro).subscribe(() => {
            this.ngOnInit();
            this.messageService.add(Mensagens.SUCESSO_PEDIDO_RECEBIDO);
        });
    }

    onCancelar() {
        this.pedidoService.cancelarPedido(this.registro.id).subscribe(() => {
            this.ngOnInit();
            this.messageService.add(Mensagens.SUCESSO_PEDIDO_CANCELADO);
        });
    }
}
