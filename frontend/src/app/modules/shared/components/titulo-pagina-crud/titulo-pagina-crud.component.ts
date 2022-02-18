import { Component, Input, OnInit } from "@angular/core";

@Component({
    selector: "titulo-pagina-crud",
    templateUrl: "./titulo-pagina-crud.component.html",
    styleUrls: ["./titulo-pagina-crud.component.scss"],
})
export class TituloPaginaCrudComponent implements OnInit {
    @Input() entidade: string;
    @Input() acao: string;
    @Input() iconeAcao: string;

    constructor() {}

    ngOnInit(): void {
        if (this.iconeAcao) {
            return;
        }
        if (!this.acao) {
            return;
        }

        switch (this.acao) {
            case "Consultar":
                this.iconeAcao = "pi pi-list";
                break;
            case "Visualizar":
                this.iconeAcao = "fa fa-fw fa-eye"
                break;
            case "Editar":
                this.iconeAcao = "fa fa-fw fa-edit"
                break;
            case "Novo":
                this.iconeAcao = "fa fa-fw fa-plus-square";
                break;
            default:
                break;
        }
    }
}
