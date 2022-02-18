import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AuditoriaModule } from './../auditoria/auditoria.module';
import { PrimengModule } from './../primeng/primeng.module';
import { SharedModule } from './../shared/shared.module';
import { DepartamentoFiltroComponent } from './components/filtro-busca/departamento-filtro-busca.component';
import {
    DepartamentoFormSectionComponent,
} from './components/form-section/departamento-form-section.component';
import { DepartamentoFormComponent } from './pages/form/departamento-form.component';
import { DepartamentoBuscaComponent } from './pages/busca/departamento-busca.component';
import { DepartamentoRoutingModule } from './departamento-routing.module';

@NgModule({
    declarations: [
        DepartamentoBuscaComponent,
        DepartamentoFiltroComponent,
        DepartamentoFormComponent,
        DepartamentoFormSectionComponent,
    ],
    imports: [
        CommonModule,
        DepartamentoRoutingModule,
        SharedModule,
        PrimengModule,
        AuditoriaModule,
    ],
})
export class DepartamentoModule {}
