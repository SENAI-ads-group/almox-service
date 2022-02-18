import { Component, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { MessageService } from "primeng/api";
import { Fornecedor } from "src/model/fornecedor";
import { CommonService } from "src/modules/shared/services/common.service";
import { HandleErrorService } from "src/modules/shared/services/handle-error.service";
import { Mensagens } from "src/utils/Mensagens";
import { rotaEstaEmModoVisualizacao } from "src/utils/RouterUtil";

import { FornecedorService } from "../../services/fornecedor.service";

@Component({
    selector: "fornecedor-form",
    templateUrl: "./fornecedor-form.component.html",
})
export class FornecedorFormComponent implements OnInit {
    fornecedor: Fornecedor = { contato: {} };

    tiposEndereco: any[];
    tiposTelefone: any[];

    editandoRegistroExistente: boolean;
    modoVisualizacao: boolean;
    @ViewChild("formulario") formulario: NgForm;

    constructor(
        private fornecedorService: FornecedorService,
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
                this.fornecedorService.buscarPorId(id).subscribe(fornecedor => {
                    this.fornecedor = fornecedor;
                });
            }
        });

        this.commonService.buscarEnumeradores().subscribe(enumaradores => {
            this.tiposEndereco = enumaradores.tiposEndereco;
            this.tiposTelefone = enumaradores.tiposTelefone;
        });

        this.modoVisualizacao = rotaEstaEmModoVisualizacao(
            this.activatedRoute.snapshot
        );
    }

    onSubmit(formulario: NgForm): void {
        if (!formulario.valid) return;

        const httpSubscriber = this.editandoRegistroExistente
            ? this.fornecedorService.atualizar(
                  this.fornecedor.id,
                  this.fornecedor
              )
            : this.fornecedorService.criar(this.fornecedor);

        httpSubscriber.subscribe(() => {
            this.messageService.add(Mensagens.SUCESSO_REGISTRO_SALVO);
            this.router.navigate(["/fornecedores/"]);
        });
    }

    limpar(): void {
        this.fornecedor;
    }

    habilitarModoEdicao = () =>
        this.router.navigate([`fornecedores/editar/${this.fornecedor.id}`]);
}
