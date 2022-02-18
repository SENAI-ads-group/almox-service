import { Component, Input, OnInit } from "@angular/core";
import { ControlContainer, NgForm } from "@angular/forms";

import { Auditavel } from "./../../../model/auditavel";

@Component({
    selector: "auditoria-form-section",
    templateUrl: "./auditoria-form-section.component.html",
    viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class AuditoriaFormSectionComponent implements OnInit {
    @Input() auditavel: Auditavel;

    constructor() {}

    ngOnInit(): void {}
}
