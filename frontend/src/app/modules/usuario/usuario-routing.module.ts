import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { UsuarioListaComponent } from './components/usuario-lista/usuario-lista.component';


const routes: Routes = [
    { path: "", component: UsuarioListaComponent },
    { path: "novo", component: UsuarioFormComponent },
    { path: "editar/:id", component: UsuarioFormComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class UsuarioRoutingModule {}
