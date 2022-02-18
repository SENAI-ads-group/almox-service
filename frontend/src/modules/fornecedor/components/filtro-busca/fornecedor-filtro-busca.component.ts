import { Component, EventEmitter, OnInit, Output } from "@angular/core";

@Component({
    selector: "fornecedor-filtro-busca",
    templateUrl: "./fornecedor-filtro-busca.component.html",
})
export class FornecedorFiltroBuscaComponent {
    @Output("buscar")
    buscarEvent: EventEmitter<any> = new EventEmitter();

    filtro = {
        cnpj: "",
        razaoSocial: "",
        email: "",
    };

    constructor() {}

    onLimpar() {
        this.filtro = {
            cnpj: "",
            razaoSocial: "",
            email: "",
        };
    }

    onBuscar() {
        this.buscarEvent.emit({ filtro: this.filtro });
    }
}
