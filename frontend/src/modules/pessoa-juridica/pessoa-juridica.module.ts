import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { PrimengModule } from './../primeng/primeng.module';
import { PjFormSectionComponent } from './components/pj-form-section/pj-form-section.component';

@NgModule({
    declarations: [PjFormSectionComponent],
    imports: [CommonModule, PrimengModule],
    exports:[PjFormSectionComponent]
})
export class PessoaJuridicaModule {

}
