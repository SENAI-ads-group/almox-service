import { Contato } from '../../../model/contato';
import { Component, Input, OnInit } from '@angular/core';
import { ControlContainer, NgForm } from '@angular/forms';

@Component({
  selector: 'contato-form-section',
  templateUrl: './contato-form-section.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],

})
export class ContatoFormSectionComponent implements OnInit {

    @Input() contato: Contato;
    @Input() modoVisualizacao: boolean;
    @Input() formulario: NgForm;
    @Input() tiposTelefones: any[];
    @Input() tiposEnderecos: any[];

  constructor() { }

  ngOnInit(): void {
  }

}
