import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { FornecedorBuscaComponent } from "./components/busca/fornecedor-busca.component";
import { FornecedorFormComponent } from "./components/form/fornecedor-form.component";

const routes: Routes = [
    { path: "", component: FornecedorBuscaComponent },
    { path: "novo", component: FornecedorFormComponent },
    { path: "visualizar/:id", component: FornecedorFormComponent },
    { path: "editar/:id", component: FornecedorFormComponent },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class FornecedorRoutingModule {}
