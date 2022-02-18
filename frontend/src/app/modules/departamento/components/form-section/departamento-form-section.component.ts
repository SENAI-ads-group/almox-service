import { Component, Input, OnInit } from "@angular/core";
import { ControlContainer, NgForm } from "@angular/forms";
import { Departamento } from "src/app/model/departamento";
import { Usuario } from "src/app/model/usuario";

@Component({
    selector: "departamento-form-section",
    templateUrl: "./departamento-form-section.component.html",
    viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class DepartamentoFormSectionComponent {
    @Input() departamento: Departamento;
    @Input() modoVisualizacao: boolean;
    @Input() usuarios: Usuario[];
    @Input() formulario: NgForm;

    constructor() {}
}
