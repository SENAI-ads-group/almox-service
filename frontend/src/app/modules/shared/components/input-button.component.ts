import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";

@Component({
    selector: "input-button",
    template: `
        <div class="p-inputgroup">
            <input
                style="cursor: pointer"
                readonly
                (click)="onClick()"
<<<<<<< HEAD
                [value]="value ? value : ''"
=======
                [value]="value"
>>>>>>> a7d33ac6e034c748fdf869da03c3fbb32b236fec
                [placeholder]="placeholder"
                pInputText
                [disabled]="disabled"
            />
            <button
                *ngIf="value"
                type="button"
                pButton
                pRipple
                icon="pi pi-times"
<<<<<<< HEAD
                styleClass="p-button-danger"
=======
                class="p-button-danger"
>>>>>>> a7d33ac6e034c748fdf869da03c3fbb32b236fec
                (click)="onButtonRemoveClick()"
            ></button>
        </div>
    `,
})
export class InputButtonComponent {
    @Output("onClick") clickEvent = new EventEmitter<any>();
    @Output("remover") removeClickEvent = new EventEmitter<any>();
    @Input("value") value: string;
    @Input("placeholder") placeholder: string;
    @Input("disabled") disabled = false;

    constructor() {}

    onClick() {
        this.clickEvent.emit({});
    }

    onButtonRemoveClick = () => this.removeClickEvent.emit({});
}
