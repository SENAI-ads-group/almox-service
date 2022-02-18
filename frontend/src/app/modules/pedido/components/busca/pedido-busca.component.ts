import { FornecedorService } from "./../../../fornecedor/services/fornecedor.service";
import { PedidoFormComponent } from "../form/pedido-form.component";
import { UsuarioService } from "../../../usuario/services/usuario.service";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ConfirmationService, MessageService } from "primeng/api";
import { Observable } from "rxjs";
import { Pedido } from "src/app/model/pedido";
import { Usuario } from "src/app/model/usuario";
import {
    criarConfiguracaoColuna,
    TipoColuna,
} from "src/app/modules/shared/components/tabela-crud/coluna";
import { CommonService } from "src/app/modules/shared/services/common.service";
import { HandleErrorService } from "src/app/modules/shared/services/handle-error.service";

import { PaginaBuscaCrud } from "../../../shared/PaginaBuscaCrud";
import { PedidoService } from "../../services/pedido.service";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";

@Component({
    selector: "pedido-busca",
    templateUrl: "./pedido-busca.component.html",
    styleUrls: ["./pedido-busca.component.scss"],
})
export class PedidoBuscaComponent extends PaginaBuscaCrud<Pedido> {
    NOME_PAGINA = "Pedidos de Compra";
    enums: any;
    compradores$: Observable<Usuario[]>;
    fornecedores$: Observable<Usuario[]>;
    colunas: any[];
    dialogRef: DynamicDialogRef;

    constructor(
        private confirmationService: ConfirmationService,
        private messageService: MessageService,
        private commonService: CommonService,
        pedidoService: PedidoService,
        private usuarioService: UsuarioService,
        private fornecedorService: FornecedorService,
        private router: Router,
        private dialogService: DialogService
    ) {
        super(pedidoService);
    }

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("dataPedido", "Data Pedido", TipoColuna.DATA_HORA),
            //criarConfiguracaoColuna("dataPrevisaoEntrega", "Previsão de Entrega", TipoColuna.DATA_HORA),
            criarConfiguracaoColuna(
                "comprador.nome",
                "Comprador",
                TipoColuna.TEXTO
            ),
            criarConfiguracaoColuna(
                "fornecedor.nomeFantasia",
                "Fornecedor",
                TipoColuna.TEXTO
            ),
        ];

        this.commonService
            .buscarEnumeradores()
            .subscribe(resp => (this.enums = resp));
        this.compradores$ = this.usuarioService.buscarTodos();
        this.fornecedores$ = this.fornecedorService.buscarTodos();
        this.onBuscar({ status: { type: "PENDENTE_ENTREGA" } });
    }

    onBuscar(filtro: any) {
        filtro.status = filtro.status
            ? filtro.status
            : { type: 'PENDENTE_ENTREGA' };

        this.loading = true;
        this.service.buscarTodosFiltrado(filtro).subscribe(
            (dados: Pedido[]) => {
                this.registros = dados;
                this.loading = false;
            },
            () => (this.loading = false)
        );
    }

    onChangeStatus({ index }) {
        this.onBuscar({ status: this.enums.statusPedido[index] });
    }

    onNovoPedido() {
        this.dialogRef = this.dialogService.open(PedidoFormComponent, {
            width: "70%",
            header: "Novo Pedido",
        });

        this.dialogRef.onClose.subscribe(() => {});
    }

    onEditar(registro: Pedido): void {
        throw new Error("Method not implemented.");
    }

    onDetalhes(registro: Pedido) {
        this.router.navigate([`pedidos/atendimento/${registro.id}`]);
    }

    onExcluir(registro: Pedido) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir a requisição ${registro.id} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {},
        });
        this.messageService.messageObserver.subscribe();
    }
}
