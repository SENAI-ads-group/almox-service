import { TabelaComponent } from './components/tabela/tabela.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { PrimengModule } from '../primeng/primeng.module';
import { LimparButtonComponent } from './components/buttons/limpar-button.component';
import { LimparFiltroButtonComponent } from './components/buttons/limpar-filtro-button.component';
import { NovoButtonComponent } from './components/buttons/novo-button.component';
import { PesquisarButtonComponent } from './components/buttons/pesquisar-button.component';
import { SubmitButtonComponent } from './components/buttons/submit-button.component';
import { FiltroStatusAuditavel } from './components/filtro-status-auditavel/filtro-status-auditavel.component';
import { InputButtonComponent } from './components/input-button.component';
import { StepComponent } from './components/step/step.component';
import { TabelaCrudComponent } from './components/tabela-crud/tabela-crud.component';
import { TituloPaginaCrudComponent } from './components/titulo-pagina-crud/titulo-pagina-crud.component';
import { CommonService } from './services/common.service';
import { HandleErrorService } from './services/handle-error.service';

@NgModule({
    declarations: [
        TabelaCrudComponent,
        FiltroStatusAuditavel,
        TituloPaginaCrudComponent,
        LimparButtonComponent,
        NovoButtonComponent,
        PesquisarButtonComponent,
        SubmitButtonComponent,
        StepComponent,
        InputButtonComponent,
        LimparFiltroButtonComponent,
        TabelaComponent
    ],
    imports: [CommonModule, PrimengModule],
    providers: [CommonService, HandleErrorService],
    exports: [
        TabelaCrudComponent,
        FiltroStatusAuditavel,
        TituloPaginaCrudComponent,
        LimparButtonComponent,
        LimparFiltroButtonComponent,
        NovoButtonComponent,
        PesquisarButtonComponent,
        SubmitButtonComponent,
        StepComponent,
        InputButtonComponent,
        TabelaComponent
    ],
})
export class SharedModule {}
