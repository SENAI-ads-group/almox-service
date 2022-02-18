import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Observable, Subscriber } from 'rxjs';
import { Departamento } from 'src/app/model/departamento';
import { FiltroConsideracaoAtivos } from 'src/app/model/enums';
import { Grupo } from 'src/app/model/grupo';
import { DepartamentoService } from 'src/app/modules/departamento/services/departamento.service';

import { Produto } from '../../../../model/produto';
import { GrupoService } from '../../../grupo/grupo.service';
import {
    criarConfiguracaoColuna,
    criarConfiguracaoColunaStatusAuditavel,
    TipoColuna,
} from '../../../shared/components/tabela-crud/coluna';
import { Mensagens } from './../../../../utils/Mensagens';
import { ProdutoService } from './../../services/produto.service';
import { ModalHistoricoComponent } from './../modal-historico/modal-historico-produto.component';

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
    dynamicDialog: DynamicDialogRef;
    mostrarDialogHistorico: boolean;

    constructor(
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private dialogService: DialogService,
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

        this.onBuscar({filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS});
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

    onEditar = (produto: Produto) => {
        this.router.navigate([`/produtos/editar/${produto.id}`])
    }


    onDetalhes(produto: Produto) {
        this.dialogService.open(ModalHistoricoComponent, {
            header: "Histórico de Estoque",
            width: "70%",
            data: produto,
        });
    }

    onExcluir(produto: Produto) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o produto ${produto.descricao} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {
                this.produtoService.excluir(produto.id).subscribe(() => {
                    this.messageService.add(Mensagens.SUCESSO_REGISTRO_EXCLUIDO);
                    this.ngOnInit();
                });
            }
        });
    }

    exibirAcaoEditar = (produto: Produto) => !produto.excluido;

    exibirAcaoExcluir = (produto: Produto) => !produto.excluido;
}
