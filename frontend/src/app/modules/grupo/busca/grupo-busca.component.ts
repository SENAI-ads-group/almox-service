import { PaginaFormularioCrud } from "./../../shared/PaginaFormularioCrud";
import { Router } from "@angular/router";
import { CommonService } from "../../shared/services/common.service";
import { HandleErrorService } from "../../shared/services/handle-error.service";
import { MessageService, ConfirmationService } from "primeng/api";
import { GrupoService } from "../grupo.service";
import { GrupoFiltroBuscaComponent } from "../filtro-busca/grupo-filtro-busca.component";
import { Observable, Subscriber } from "rxjs";
import { Grupo } from "../../../model/grupo";
import { Component, OnInit, ViewChild } from "@angular/core";
import {
    criarConfiguracaoColuna,
    criarConfiguracaoColunaStatusAuditavel,
    TipoColuna,
} from "../../shared/components/tabela-crud/coluna";
import { PaginaBuscaCrud } from "../../shared/PaginaBuscaCrud";

@Component({
    selector: "grupo-lista",
    templateUrl: "./grupo-busca.component.html",
})
export class GrupoBuscaComponent extends PaginaBuscaCrud<Grupo> {
    TITULO_PAGINA = "Grupos de Produto";

    enums$: Observable<any>;
    colunas: any[];

    @ViewChild("filtroComponent") filtroComponent: GrupoFiltroBuscaComponent;

    constructor(
        grupoService: GrupoService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private handleErrorService: HandleErrorService,
        private commonService: CommonService,
        private router: Router
    ) {
        super(grupoService);
    }

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("descricao", "Descrição", TipoColuna.TEXTO),
            criarConfiguracaoColuna(
                "dataCriacao",
                "Criação",
                TipoColuna.DATA_HORA
            ),
            criarConfiguracaoColuna(
                "dataAlteracao",
                "Última Alteração",
                TipoColuna.DATA_HORA
            ),
            criarConfiguracaoColunaStatusAuditavel("Status"),
        ];
        this.onBuscar({});
    }

    onBuscar(filtro: any): void {
        this.loading = true;
        this.service.buscarTodosFiltrado(filtro).subscribe(
            dados => {
                this.registros = dados;
                this.loading = false;
            },
            () => (this.loading = false)
        );
    }

    onEditar = (grupo: Grupo) =>
        this.router.navigate([`grupos/editar/${grupo.id}`]);

    onExcluir(grupo: Grupo) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o grupo ${grupo.descricao} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {
                this.service.excluir(grupo.id).subscribe(
                    sucess => {
                        this.messageService.add({
                            severity: "sucess",
                            summary: "Sucesso",
                            detail: "Grupo Excluído",
                            life: 3000,
                        });
                        this.onBuscar(this.filtroComponent.filtro);
                    },
                    error => this.handleErrorService.handleError(error)
                );
            },
        });
        this.messageService.messageObserver.subscribe();
    }

    exibirAcaoEditar = (grupo: Grupo) => !grupo.excluido;

    exibirAcaoExcluir = (grupo: Grupo) => !grupo.excluido;
}
