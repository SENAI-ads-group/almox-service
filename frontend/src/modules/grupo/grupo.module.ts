import { SharedModule } from './../shared/shared.module';
import { PrimengModule } from './../primeng/primeng.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GrupoRoutingModule } from './grupo-routing.module';
import { GrupoBuscaComponent } from './busca/grupo-busca.component';
import { GrupoFormComponent } from './form/grupo-form.component';
import { GrupoFiltroBuscaComponent } from './filtro-busca/grupo-filtro-busca.component';


@NgModule({
  declarations: [
    GrupoBuscaComponent,
    GrupoFormComponent,
    GrupoFiltroBuscaComponent
  ],
  imports: [
    CommonModule,
    GrupoRoutingModule,
    PrimengModule,
    SharedModule
  ]
})
export class GrupoModule { }
