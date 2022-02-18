import { Component, EventEmitter, Output } from "@angular/core";

@Component({
    selector: "pesquisar-button",
    template: `
        <button
            pButton
            pRipple
            label="Buscar"
            icon="pi pi-search"
            class="p-button-outlined p-mb-3"
        ></button>
    `,
})
export class PesquisarButtonComponent {
    constructor() {}
}
