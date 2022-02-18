import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { PrimengModule } from '../primeng/primeng.module';
import { BarraSuperiorComponent } from './barra-superior/barra-superior.component';
import { CoreRoutingModule } from './core-routing.module';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { RodapeComponent } from './rodape/rodape.component';
import { TituloPaginaComponent } from './titulo-pagina/titulo-pagina.component';

@NgModule({
    declarations: [
        LoginComponent,
        BarraSuperiorComponent,
        MainComponent,
        RodapeComponent,
        TituloPaginaComponent,
    ],
    imports: [CommonModule, CoreRoutingModule, PrimengModule],
})
export class CoreModule {}
