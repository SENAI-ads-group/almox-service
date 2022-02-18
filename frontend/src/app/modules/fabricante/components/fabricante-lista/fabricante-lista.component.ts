import { HandleErrorService } from "src/app/modules/shared/services/handle-error.service";
import { FabricanteService } from "./../../services/fabricante.service";
import { ConfirmationService, MessageService } from "primeng/api";
import { Router } from "@angular/router";
import { Fabricante } from "./../../../../model/fabricante";
import { Component, OnInit, ViewChild } from "@angular/core";
import { criarConfiguracaoColuna, TipoColuna,} from "src/app/modules/shared/components/tabela-crud/coluna";
import { FabricanteFiltroComponent } from "../fabricante-filtro/fabricante-filtro.component";

@Component({
    selector: "fabricante-lista",
    templateUrl: "./fabricante-lista.component.html",
})
export class FabricanteListaComponent implements OnInit {
    colunas = [];
    fabricantes: Fabricante[];
    selecionados: Fabricante[];
    @ViewChild("filtroComponent") filtroComponent: FabricanteFiltroComponent;

    constructor(
        private router: Router,
        private confirmationService: ConfirmationService,
        private messageService: MessageService,
        private fabricanteService: FabricanteService,
        private handleErrorService: HandleErrorService
    ) {}

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("cnpj", "CNPJ", TipoColuna.TEXTO),
            criarConfiguracaoColuna(
                "razaoSocial",
                "Razão Social",
                TipoColuna.TEXTO
            ),
            criarConfiguracaoColuna(
                "nomeFantasia",
                "Nome Fantasia",
                TipoColuna.TEXTO
            ),
        ];
        this.handleBuscar({});
    }
    handleVisualizar(fabricante: Fabricante) {
        this.router.navigate([`fabricantes/visualizar/${fabricante.id}`]);
    }
    handleEditar(fabricante: Fabricante) {
        this.router.navigate([`fabricantes/editar/${fabricante.id}`]);
    }
    handleExcluir(fabricante: Fabricante) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o fabricante ${fabricante.razaoSocial} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {
                this.fabricanteService.excluir(fabricante.id).subscribe(
                    success => {
                        this.messageService.add({
                            severity: "success",
                            summary: "Sucesso",
                            detail: "Fabricante Excluído",
                            life: 3000,
                        });
                        this.handleBuscar(this.filtroComponent.filtro);
                    },
                    error => this.handleErrorService.handleError(error)
                );
            },
        });
        this.messageService.messageObserver.subscribe();
    }

    handleBuscar(filtro: any) {
        this.fabricanteService
            .buscarTodosFiltrado(filtro)
            .subscribe(
                fabricantesEncontrados =>
                    (this.fabricantes = fabricantesEncontrados)
            );
    }

    handleExibirAcaoEditar(fabricante: Fabricante) {
        return true;
    }
    handleExibirAcaoExcluir(fabricante: Fabricante) {
        return true;
    }
}
