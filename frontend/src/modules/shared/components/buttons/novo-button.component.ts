import { Component, Input } from "@angular/core";

@Component({
    selector: "novo-button",
    template: `
        <button *ngIf="!semRota"
            pButton
            pRipple
            type="button"
            [label]="label"
            icon="pi pi-plus"
            class="p-mr-1 p-mb-3 p-shadow-3"
            routerLink="novo"
            style="background: #C8E6C9;color: #256029; font-weight: 700; font-size: 14px;"
        ></button>

        <button *ngIf="semRota"
            pButton
            pRipple
            type="button"
            [label]="label"
            icon="pi pi-plus"
            class="p-mr-1 p-mb-3 p-shadow-3"
            style="background: #C8E6C9;color: #256029; font-weight: 700; font-size: 14px;"
        ></button>
    `,
})
export class NovoButtonComponent {
    @Input() label = "Novo";
    @Input() semRota : boolean;

    constructor() {}
}
