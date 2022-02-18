import { Component } from '@angular/core';

@Component({
    selector: "limpar-filtro-button",
    template: `
        <button
            pButton
            pRipple
            type="button"
            label="Limpar"
            class="p-mb-3 p-mr-1 p-button-secondary p-button-outlined"
            style="color: #424242;"
        ></button>
    `,
})
export class LimparFiltroButtonComponent {
    constructor() {}
}
