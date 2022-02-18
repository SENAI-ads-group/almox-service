import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { ConfirmationService, MessageService } from "primeng/api";
import { Observable, Subscriber } from "rxjs";
import { Usuario } from "src/model/usuario";
import {
    criarConfiguracaoColuna,
    criarConfiguracaoColunaStatusAuditavel,
    TipoColuna,
} from "src/modules/shared/components/tabela-crud/coluna";
import { CommonService } from "src/modules/shared/services/common.service";
import { HandleErrorService } from "src/modules/shared/services/handle-error.service";

import { UsuarioService } from "../../services/usuario.service";
import { UsuarioFiltroComponent } from "../usuario-filtro/usuario-filtro.component";

@Component({
    selector: "usuario-lista",
    templateUrl: "./usuario-lista.component.html",
})
export class UsuarioListaComponent implements OnInit {
    TITULO_PAGINA = "Usuários";

    usuarios: Usuario[];
    selecionados: Usuario[];
    enums$: Observable<any>;
    enumsSubscribe: Subscriber<any>;
    tiposUsuarios: any[];
    colunas: any[];
    @ViewChild("filtroComponent") filtroComponent: UsuarioFiltroComponent;

    constructor(
        private usuarioService: UsuarioService,
        private messageService: MessageService,
        private handleErrorService: HandleErrorService,
        private confirmationService: ConfirmationService,
        private commonService: CommonService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.commonService.buscarEnumeradores().subscribe(
            enumeradores => (this.tiposUsuarios = enumeradores.tiposUsuarios),
            error => this.handleErrorService.handleError(error)
        );

        this.colunas = [
            criarConfiguracaoColuna("id", "#", TipoColuna.TEXTO),
            criarConfiguracaoColuna("nome", "Nome", TipoColuna.TEXTO),
            criarConfiguracaoColuna("email", "Email", TipoColuna.TEXTO),
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
            criarConfiguracaoColuna(
                "tipoUsuario.descricao",
                "Cargo",
                TipoColuna.TEXTO
            ),
            criarConfiguracaoColunaStatusAuditavel("Status"),
        ];
    }

    buscar(filtro: any): void {
        this.usuarioService
            .buscarTodosFiltrado(filtro)
            .subscribe(
                usuariosEncontrados => (this.usuarios = usuariosEncontrados)
            );
    }

    visualizar = (usuario: Usuario) =>
        this.router.navigate([`usuarios/visualizar/${usuario.id}`]);

    editar = (usuario: Usuario) =>
        this.router.navigate([`usuarios/editar/${usuario.id}`]);

    excluirSelecionados(): void {
        this.confirmationService.confirm({
            message: "Tem certeza que deseja excluir os usuários selecionados?",
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            rejectLabel: "Não",
            acceptLabel: "Sim",
            accept: () => {
                this.selecionados
                    .filter(usuario => !usuario.excluido)
                    .forEach(usuario => {
                        this.usuarioService.excluir(usuario.id).subscribe(
                            success => {
                                this.messageService.add({
                                    severity: "success",
                                    summary: "Sucesso",
                                    detail: `Usuário ${usuario.username} excluído.`,
                                    life: 1500,
                                });
                                this.buscar(this.filtroComponent.filtro);
                            },
                            error => this.handleErrorService.handleError(error)
                        );
                    });
            },
        });
    }

    excluir(usuario: Usuario) {
        this.confirmationService.confirm({
            message: `Você têm certeza que deseja excluir o usuário ${usuario.username} ?`,
            header: "Confirmação",
            icon: "pi pi-exclamation-triangle",
            acceptLabel: "Sim",
            rejectLabel: "Não",
            accept: () => {
                this.usuarioService.excluir(usuario.id).subscribe(
                    success => {
                        this.messageService.add({
                            severity: "success",
                            summary: "Sucesso",
                            detail: "Usuário Excluído",
                            life: 3000,
                        });
                        this.buscar(this.filtroComponent.filtro);
                    },
                    error => this.handleErrorService.handleError(error)
                );
            },
        });
        this.messageService.messageObserver.subscribe();
    }

    exibirAcaoEditar = (usuario: Usuario) => !usuario.excluido;

    exibirAcaoExcluir = (usuario: Usuario) => !usuario.excluido;
}
