import { Component, Input, OnInit } from '@angular/core';
import { ControlContainer, NgForm } from '@angular/forms';
import { Fornecedor } from 'src/app/model/fornecedor';

@Component({
  selector: 'fornecedor-form-section',
  templateUrl: './fornecedor-form-section.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],

})
export class FornecedorFormSectionComponent {

    @Input() fornecedor: Fornecedor;
    @Input() tiposFornecedors: any[];
    @Input() modoVisualizacao: boolean;
    @Input() formulario: NgForm;

    @Input() tiposTelefones: any[];
    @Input() tiposEnderecos: any[];

  constructor() { }


}
