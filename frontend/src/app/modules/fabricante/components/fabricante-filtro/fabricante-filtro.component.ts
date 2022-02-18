import { Component, EventEmitter, OnInit, Output } from "@angular/core";

@Component({
    selector: "fabricante-filtro",
    templateUrl: "./fabricante-filtro.component.html",
})
export class FabricanteFiltroComponent implements OnInit {
    @Output("buscar")
    buscarEvent: EventEmitter<any> = new EventEmitter();

    filtro = {
        cnpj: "",
        razaoSocial: "",
        email: "",
    };

    constructor() {}

    ngOnInit(): void {}
    handleLimpar() {
        this.filtro = {
            cnpj: "",
            razaoSocial: "",
            email: "",
        };
    }
    handleBuscar() {
        this.buscarEvent.emit({ filtro: this.filtro });
    }
}
