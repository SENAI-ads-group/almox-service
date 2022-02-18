import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GrupoBuscaComponent } from './busca/grupo-busca.component';
import { GrupoFormComponent } from './form/grupo-form.component';

const routes: Routes = [
  { path: "", component: GrupoBuscaComponent },
  { path: "novo", component: GrupoFormComponent },
  { path: "visualizar/:id", component: GrupoFormComponent },
  { path: "editar/:id", component: GrupoFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GrupoRoutingModule {}
