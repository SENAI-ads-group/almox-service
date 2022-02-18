import { PessoaJuridicaModule } from "./../pessoa-juridica/pessoa-juridica.module";
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { PrimengModule } from "../primeng/primeng.module";
import { SharedModule } from "../shared/shared.module";

import { FabricanteFiltroComponent } from "./components/fabricante-filtro/fabricante-filtro.component";
import { FabricanteFormSectionComponent } from "./components/fabricante-form-section/fabricante-form-section.component";
import { FabricanteFormComponent } from "./components/fabricante-form/fabricante-form.component";
import { FabricanteListaComponent } from "./components/fabricante-lista/fabricante-lista.component";
import { FabricanteRoutingModule } from "./fabricante-routing.module";
import { ContatoModule } from "../contato/contato.module";

@NgModule({
    declarations: [
        FabricanteListaComponent,
        FabricanteFiltroComponent,
        FabricanteFormComponent,
        FabricanteFormSectionComponent,
    ],

    imports: [
        CommonModule,
        FabricanteRoutingModule,
        SharedModule,
        PrimengModule,
        PessoaJuridicaModule,
        ContatoModule,
    ],
})
export class FabricanteModule {}
