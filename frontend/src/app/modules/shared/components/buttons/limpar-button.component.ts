import { Component } from '@angular/core';

@Component({
    selector: "limpar-button",
    template: `
        <button
            pButton
            pRipple
            type="button"
            label="Limpar"
            class="p-mb-3 p-mr-1 p-button-secondary"
        ></button>
    `,
})
export class LimparButtonComponent {
    constructor() {}
}
