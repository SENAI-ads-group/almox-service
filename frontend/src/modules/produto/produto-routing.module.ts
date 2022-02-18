import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProdutoFormComponent } from './components/form/produto-form.component';
import { ProdutoBuscaComponent } from './components/busca/produto-busca.component';

const routes: Routes = [
    { path: "", component: ProdutoBuscaComponent },
    { path: "novo", component: ProdutoFormComponent },
    { path: "editar/:id", component: ProdutoFormComponent },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ProdutoRoutingModule {}
