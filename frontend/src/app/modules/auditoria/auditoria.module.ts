import { PrimengModule } from "./../primeng/primeng.module";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AuditoriaFormSectionComponent } from "./auditoria-form-section/auditoria-form-section.component";

@NgModule({
    declarations: [AuditoriaFormSectionComponent],
    imports: [CommonModule, PrimengModule],
    exports: [AuditoriaFormSectionComponent],
})
export class AuditoriaModule {}
