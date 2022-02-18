import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Observable, Subscriber } from 'rxjs';

import {
    criarConfiguracaoColuna,
    criarConfiguracaoColunaStatusAuditavel,
    TipoColuna,
} from '../../../shared/components/tabela-crud/coluna';
import { CommonService } from '../../../shared/services/common.service';
import { HandleErrorService } from '../../../shared/services/handle-error.service';
import { Departamento } from '../../../../model/departamento';
import { DepartamentoService } from '../../services/departamento.service';

@Component({
    selector: "departamento-lista",
    templateUrl: "./departamento-busca.component.html",
})
export class DepartamentoBuscaComponent implements OnInit {
    departamentos: Departamento[];
    selecionados: Departamento[];
    enums$: Observable<any>;
    enumsSubscribe: Subscriber<any>;
    colunas: any[];

    constructor(
        private departamentoService: DepartamentoService,
        private messageService: MessageService,
        private handleErrorService: HandleErrorService,
        private confirmationService: ConfirmationService,
        private commonService: CommonService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("nome", "Nome", TipoColuna.TEXTO),
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
        this.buscar({});
    }

    buscar(filtro: any): void {
        this.departamentoService
            .buscarTodosFiltrado(filtro)
            .subscribe(
                departamentosEncontrados =>
                    (this.departamentos = departamentosEncontrados)
            );
    }

    visualizar = (departamento: Departamento) =>
        this.router.navigate([`departamentos/visualizar/${departamento.id}`]);

    editar = (departamento: Departamento) =>
        this.router.navigate([`departamentos/editar/${departamento.id}`]);

    excluirSelecionados(): void {
        this.confirmationService.confirm({
            message:
                "Tem certeza que deseja excluir os departamentos selecionados?",
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            rejectLabel: "Não",
            acceptLabel: "Sim",
            accept: () => {},
        });
    }

    excluir(departamento: Departamento) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o departamento ${departamento.nome} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {},
        });
        this.messageService.messageObserver.subscribe();
    }

    exibirAcaoEditar = (departamento: Departamento) => !departamento.excluido;

    exibirAcaoExcluir = (departamento: Departamento) => !departamento.excluido;
}
