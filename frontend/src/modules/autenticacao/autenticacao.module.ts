import { FormsModule } from '@angular/forms';
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { AutenticacaoRoutingModule } from "./autenticacao-routing.module";
import { LoginComponent } from "./components/login/login.component";
import { PrimengModule } from '../primeng/primeng.module';

@NgModule({
    declarations: [LoginComponent],
    imports: [CommonModule, AutenticacaoRoutingModule, FormsModule, PrimengModule],
})
export class AutenticacaoModule {}
