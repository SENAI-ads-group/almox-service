import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ContatoModule } from '../contato/contato.module';
import { PessoaJuridicaModule } from '../pessoa-juridica/pessoa-juridica.module';
import { SharedModule } from '../shared/shared.module';
import { PrimengModule } from './../primeng/primeng.module';
import { FornecedorBuscaComponent } from './components/busca/fornecedor-busca.component';
import { FornecedorFiltroBuscaComponent } from './components/filtro-busca/fornecedor-filtro-busca.component';
import { FornecedorFormSectionComponent } from './components/form-section/fornecedor-form-section.component';
import { FornecedorFormComponent } from './components/form/fornecedor-form.component';
import { FornecedorModalListaComponent } from './components/modal-listagem/fornecedor-modal-lista.component';
import { FornecedorRoutingModule } from './fornecedor-routing.module';

@NgModule({
    declarations: [
        FornecedorModalListaComponent,
        FornecedorFormComponent,
        FornecedorFormSectionComponent,
        FornecedorBuscaComponent,
        FornecedorFiltroBuscaComponent,
    ],
    imports: [
        CommonModule,
        FornecedorRoutingModule,
        SharedModule,
        PrimengModule,
        PessoaJuridicaModule,
        ContatoModule,
    ],
    exports: [FornecedorModalListaComponent],
})
export class FornecedorModule {}
