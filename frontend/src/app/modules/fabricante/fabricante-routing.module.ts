import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { FabricanteFormComponent } from "./components/fabricante-form/fabricante-form.component";
import { FabricanteListaComponent } from "./components/fabricante-lista/fabricante-lista.component";

const routes: Routes = [
    { path: "", component: FabricanteListaComponent },
    { path: "novo", component: FabricanteFormComponent },
    { path: "visualizar/:id", component: FabricanteFormComponent },
    { path: "editar/:id", component: FabricanteFormComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class FabricanteRoutingModule {}
