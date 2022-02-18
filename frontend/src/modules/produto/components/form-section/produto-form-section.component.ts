import { Component, Input, OnInit } from "@angular/core";
import { ControlContainer, NgForm } from "@angular/forms";
import { Departamento } from "src/model/departamento";
import { Produto } from "src/model/produto";

import { Fabricante } from "./../../../../model/fabricante";
import { Grupo } from "./../../../../model/grupo";

@Component({
    selector: "produto-form-section",
    templateUrl: "./produto-form-section.component.html",
    viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class ProdutoFormSectionComponent implements OnInit {
    @Input() produto: Produto;
    @Input() departamentos: Departamento[];
    @Input() fabricantes: Fabricante[];
    @Input() grupos: Grupo[];
    @Input() enums: any;

    constructor(public formulario: NgForm) {}

    ngOnInit(): void {
    }
}
