import { Component, Input } from "@angular/core";
import { ControlContainer, NgForm } from "@angular/forms";
import { Fabricante } from "src/app/model/fabricante";

@Component({
    selector: "fabricante-form-section",
    templateUrl: "./fabricante-form-section.component.html",
    viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class FabricanteFormSectionComponent {
    @Input() fabricante: Fabricante;
    @Input() tiposFabricantes: any[];
    @Input() modoVisualizacao: boolean;
    @Input() formulario: NgForm;

    @Input() tiposTelefones: any[];
    @Input() tiposEnderecos: any[];

    constructor() {}
}
