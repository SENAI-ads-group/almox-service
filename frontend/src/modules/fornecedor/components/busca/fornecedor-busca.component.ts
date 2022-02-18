import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { ConfirmationService, MessageService } from "primeng/api";
import { Fornecedor } from "src/model/fornecedor";
import {
    criarConfiguracaoColuna,
    TipoColuna,
} from "src/modules/shared/components/tabela-crud/coluna";

import { FornecedorService } from "../../services/fornecedor.service";
import { FornecedorFiltroBuscaComponent } from "../filtro-busca/fornecedor-filtro-busca.component";

@Component({
    selector: "fornecedor-lista",
    templateUrl: "./fornecedor-busca.component.html",
})
export class FornecedorBuscaComponent implements OnInit {
    colunas = [];
    fornecedores: Fornecedor[];
    selecionados: Fornecedor[];

    @ViewChild("filtroComponent")
    filtroComponent: FornecedorFiltroBuscaComponent;

    constructor(
        private router: Router,
        private confirmationService: ConfirmationService,
        private messageService: MessageService,
        private fornecedorService: FornecedorService
    ) {}

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("cnpj", "Cnpj", TipoColuna.TEXTO),
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

        this.onBuscar({});
    }

    onVisualizar(fornecedor: Fornecedor) {
        this.router.navigate([`fornecedores/visualizar/${fornecedor.id}`]);
    }

    onEditar(fornecedor: Fornecedor) {
        this.router.navigate([`fornecedores/editar/${fornecedor.id}`]);
    }

    onExcluir(fornecedor: Fornecedor) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o fornecedor ${fornecedor.razaoSocial} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {
                this.fornecedorService.excluir(fornecedor.id).subscribe(() => {
                    this.messageService.add({
                        severity: "success",
                        summary: "Sucesso",
                        detail: "Fornecedor Excluído",
                        life: 3000,
                    });
                    this.onBuscar(this.filtroComponent.filtro);
                });
            },
        });
        this.messageService.messageObserver.subscribe();
    }

    onBuscar(filtro: any) {
        this.fornecedorService
            .buscarTodosFiltrado(filtro)
            .subscribe(
                fornecedoresEncontrados =>
                    (this.fornecedores = fornecedoresEncontrados)
            );
    }

    onExibirAcaoEditar(fornecedor: Fornecedor) {
        return true;
    }
    onExibirAcaoExcluir(fornecedor: Fornecedor) {
        return true;
    }
}
