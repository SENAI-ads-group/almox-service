import { RequisicaoFormComponent } from "./../form/requisicao-form.component";
import { UsuarioService } from "./../../../usuario/services/usuario.service";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ConfirmationService, MessageService } from "primeng/api";
import { Observable } from "rxjs";
import { Requisicao } from "src/model/requisicao";
import { Usuario } from "src/model/usuario";
import {
    criarConfiguracaoColuna,
    TipoColuna,
} from "src/modules/shared/components/tabela-crud/coluna";
import { CommonService } from "src/modules/shared/services/common.service";
import { HandleErrorService } from "src/modules/shared/services/handle-error.service";

import { PaginaBuscaCrud } from "../../../shared/PaginaBuscaCrud";
import { RequisicaoService } from "../../services/requisicao.service";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";

@Component({
    selector: "requisicao-busca",
    templateUrl: "./requisicao-busca.component.html",
    styleUrls: ["./requisicao-busca.component.scss"],
})
export class RequisicaoBuscaComponent extends PaginaBuscaCrud<Requisicao> {
    NOME_PAGINA = "Requisições";
    enums: any;
    almoxarifes$: Observable<Usuario[]>;
    requisitantes$: Observable<Usuario[]>;
    colunas: any[];
    dialogRef: DynamicDialogRef;

    constructor(
        private confirmationService: ConfirmationService,
        private messageService: MessageService,
        private commonService: CommonService,
        requisicaoService: RequisicaoService,
        private usuarioService: UsuarioService,
        private router: Router,
        private dialogService: DialogService
    ) {
        super(requisicaoService);
    }

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna(
                "dataRequisicao",
                "Data",
                TipoColuna.DATA_HORA
            ),
            criarConfiguracaoColuna(
                "requisitante.nome",
                "Requisitante",
                TipoColuna.TEXTO
            ),
            criarConfiguracaoColuna(
                "almoxarife.nome",
                "Almoxarife",
                TipoColuna.TEXTO
            ),
            criarConfiguracaoColuna(
                "departamento.nome",
                "Departamento",
                TipoColuna.TEXTO
            ),
        ];

        this.commonService
            .buscarEnumeradores()
            .subscribe(resp => (this.enums = resp));
        this.almoxarifes$ = this.usuarioService.buscarTodos();
        this.requisitantes$ = this.usuarioService.buscarTodos();

        this.onBuscar({});
    }

    onBuscar(filtro: any) {
        filtro.status = filtro.status
            ? filtro.status
            : { type: 'AGUARDANDO_ATENDIMENTO' };

        this.loading = true;
        this.service.buscarTodosFiltrado(filtro).subscribe(
            (dados: Requisicao[]) => {
                this.registros = dados;
                this.loading = false;
            },
            () => (this.loading = false)
        );
    }

    onChangeStatus({ index }) {
        this.onBuscar({ status: this.enums.statusRequisicao[index] });
    }

    onNovaRequisicao() {
        this.dialogRef = this.dialogService.open(RequisicaoFormComponent, {
            width: "70%",
            header: "Nova Requisição",
        });

        this.dialogRef.onClose.subscribe(() => {});
    }

    onEditar(registro: Requisicao) {
        this.router.navigate([`requisicoes/informacoes/${registro.id}`]);
    }

    onDetalhes(registro: Requisicao) {
        this.router.navigate([`requisicoes/informacoes/${registro.id}`]);
    }

    onExcluir(registro: Requisicao) {
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
