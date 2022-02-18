import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { DepartamentoService } from '../departamento/services/departamento.service';
import { PrimengModule } from '../primeng/primeng.module';
import { SharedModule } from '../shared/shared.module';
import { AuditoriaModule } from './../auditoria/auditoria.module';
import { UsuarioFiltroComponent } from './components/usuario-filtro/usuario-filtro.component';
import { UsuarioFormSectionComponent } from './components/usuario-form-section/usuario-form-section.component';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { UsuarioListaComponent } from './components/usuario-lista/usuario-lista.component';
import { UsuarioRoutingModule } from './usuario-routing.module';

@NgModule({
    declarations: [
        UsuarioFormComponent,
        UsuarioListaComponent,
        UsuarioFiltroComponent,
        UsuarioFormSectionComponent,
    ],
    imports: [
        CommonModule,
        UsuarioRoutingModule,
        PrimengModule,
        SharedModule,
        AuditoriaModule,
    ],
    providers: [DepartamentoService],
})
export class UsuarioModule {}
