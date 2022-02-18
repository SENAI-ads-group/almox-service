import { Component, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { MessageService } from "primeng/api";
import { Observable } from "rxjs";
import { Departamento } from "src/app/model/departamento";
import { Usuario } from "src/app/model/usuario";
import { DepartamentoService } from "src/app/modules/departamento/services/departamento.service";
import { CommonService } from "src/app/modules/shared/services/common.service";
import { HandleErrorService } from "src/app/modules/shared/services/handle-error.service";
import { rotaEstaEmModoVisualizacao } from "src/app/utils/RouterUtil";

import { UsuarioService } from "../../services/usuario.service";

@Component({
    selector: "usuario-form",
    templateUrl: "./usuario-form.component.html",
})
export class UsuarioFormComponent implements OnInit {
    usuario: Usuario = {};
    departamentos$: Observable<Departamento[]>;
    tiposUsuarios: any[];
    editandoRegistroExistente: boolean;
    modoVisualizacao: boolean;
    @ViewChild("formulario") formulario: NgForm;

    constructor(
        private usuarioService: UsuarioService,
        private departamentoService: DepartamentoService,
        private handleErrorService: HandleErrorService,
        private messageService: MessageService,
        private commonService: CommonService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.activatedRoute.params.subscribe(params => {
            const id: number = params["id"];
            if (id) {
                this.editandoRegistroExistente = true;
                this.usuarioService.buscarPorId(id).subscribe(usuario => {
                    this.usuario = usuario;
                });
            }
        });

        this.commonService.buscarEnumeradores().subscribe(
            enumeradores => (this.tiposUsuarios = enumeradores.tiposUsuarios),
            error => this.handleErrorService.handleError(error)
        );
        this.departamentos$ = this.departamentoService.buscarTodos();
        this.modoVisualizacao = rotaEstaEmModoVisualizacao(
            this.activatedRoute.snapshot
        );
    }

    onSubmit(formulario: NgForm): void {
        if (!formulario.valid) return;
        const httpSubscriber = this.editandoRegistroExistente
            ? this.usuarioService.atualizar(this.usuario.id, this.usuario)
            : this.usuarioService.criar(this.usuario);
        httpSubscriber.subscribe(
            () => this.router.navigate(["/usuarios/"]),
            error => this.handleErrorService.handleError(error)
        );
    }

    limpar(): void {
        this.usuario = {};
    }

    habilitarModoEdicao = () =>
        this.router.navigate([`usuarios/editar/${this.usuario.id}`]);
}
