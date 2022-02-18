import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ConfirmationService, MessageService } from "primeng/api";
import { Observable, Subscriber } from "rxjs";
import { catchError, tap } from "rxjs/operators";
import { Departamento } from "src/app/model/departamento";
import { Grupo } from "src/app/model/grupo";
import { DepartamentoService } from "src/app/modules/departamento/services/departamento.service";

import { Produto } from "../../../../model/produto";
import { GrupoService } from "../../../grupo/grupo.service";
import {
    criarConfiguracaoColuna,
    criarConfiguracaoColunaStatusAuditavel,
    TipoColuna,
} from "../../../shared/components/tabela-crud/coluna";
import { CommonService } from "../../../shared/services/common.service";
import { ProdutoService } from "./../../services/produto.service";

@Component({
    selector: "produto-lista",
    templateUrl: "./produto-busca.component.html",
})
export class ProdutoBuscaComponent implements OnInit {
    TITULO_PAGINA = "Produtos";

    registros: Produto[];
    selecionados: Produto[];
    enums$: Observable<any>;
    grupos$: Observable<Grupo[]>;
    departamentos$: Observable<Departamento[]>;
    enumsSubscribe: Subscriber<any>;
    colunas: any[];
    loading: boolean = false;

    constructor(
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private commonService: CommonService,
        private produtoService: ProdutoService,
        private grupoService: GrupoService,
        private departamentoService: DepartamentoService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("descricao", "Descrição", TipoColuna.TEXTO),
            criarConfiguracaoColuna(
                "grupo.descricao",
                "Grupo",
                TipoColuna.TEXTO
            ),
            criarConfiguracaoColuna(
                "fabricante.razaoSocial",
                "Fabricante",
                TipoColuna.TEXTO
            ),
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
        this.grupos$ = this.grupoService.buscarTodos();
        this.departamentos$ = this.departamentoService.buscarTodos();

        this.onBuscar({});
    }

    onBuscar(filtro: any): void {
        this.loading = true;
        this.produtoService.buscarTodosFiltrado(filtro).subscribe(
            dados => {
                this.registros = dados;
                this.loading = false;
            },
            () => (this.loading = false)
        );
    }

    onEditar = (produto: Produto) =>
        this.router.navigate([`produtos/editar/${produto.id}`]);

    onExcluir(produto: Produto) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o produto ${produto.descricao} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {},
        });
        this.messageService.messageObserver.subscribe();
    }

    exibirAcaoEditar = (produto: Produto) => !produto.excluido;

    exibirAcaoExcluir = (produto: Produto) => !produto.excluido;
}
