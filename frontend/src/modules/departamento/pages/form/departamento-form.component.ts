import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Departamento } from '../../../../model/departamento';
import { Usuario } from '../../../../model/usuario';
import { HandleErrorService } from '../../../shared/services/handle-error.service';
import { rotaEstaEmModoVisualizacao } from '../../../../utils/RouterUtil';
import { UsuarioService } from '../../../usuario/services/usuario.service';
import { DepartamentoService } from '../../services/departamento.service';

@Component({
    selector: "departamento-form",
    templateUrl: "./departamento-form.component.html",
})
export class DepartamentoFormComponent implements OnInit {
    editandoRegistroExistente: boolean;
    modoVisualizacao: boolean;
    departamento: Departamento = { usuarios: [] };
    usuarios: Usuario[];

    constructor(
        private departamentoService: DepartamentoService,
        private usuarioService: UsuarioService,
        private handleErrorService: HandleErrorService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.activatedRoute.params.subscribe(params => {
            const id: number = params["id"];
            if (id) {
                this.editandoRegistroExistente = true;
                this.departamentoService
                    .buscarPorId(id)
                    .subscribe(departamento => {
                        this.departamento = departamento;
                        this.filtrarOpcoesUsuarioParaDepartamento(departamento);
                    });
            } else {
                this.usuarioService
                    .buscarTodos()
                    .subscribe(
                        usuariosEcontrados =>
                            (this.usuarios = usuariosEcontrados)
                    );
            }
        });
        this.modoVisualizacao = rotaEstaEmModoVisualizacao(
            this.activatedRoute.snapshot
        );
    }

    filtrarOpcoesUsuarioParaDepartamento = (departamento: Departamento) => {
        this.usuarioService.buscarTodos().subscribe(usuariosEcontrados => {
            this.usuarios = usuariosEcontrados.filter(usr => {
                const idsUsuariosAssociadosDepartamento =
                    departamento.usuarios.map(
                        usuarioDepartamento => usuarioDepartamento.id
                    );
                return idsUsuariosAssociadosDepartamento.indexOf(usr.id) === -1; // usuário encontrado não está associado ao departamento
            });
        });
    };

    onSubmit(formulario: NgForm): void {
        const httpSubscriber = this.editandoRegistroExistente
            ? this.departamentoService.atualizar(
                  this.departamento.id,
                  this.departamento
              )
            : this.departamentoService.criar(this.departamento);
        httpSubscriber.subscribe(
            () => {
                this.router.navigate(["/departamentos/"]);
            },
            error => this.handleErrorService.handleError(error)
        );
    }

    limpar(): void {
        this.departamento = {};
    }

    habilitarModoEdicao = () =>
        this.router.navigate([`departamentos/editar/${this.departamento.id}`]);
}
