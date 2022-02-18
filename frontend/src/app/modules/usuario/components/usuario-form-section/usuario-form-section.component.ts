import { AfterViewInit, ViewChild } from "@angular/core";
import { Component, Input } from "@angular/core";
import { ControlContainer, NgForm } from "@angular/forms";
import { Usuario } from "src/app/model/usuario";

@Component({
    selector: "usuario-form-section",
    templateUrl: "./usuario-form-section.component.html",
    viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class UsuarioFormSectionComponent {
    @Input() usuario: Usuario;
    @Input() tiposUsuarios: any[];
    @Input() modoVisualizacao: boolean;
    @Input() formulario: NgForm;

    constructor() {}
}
