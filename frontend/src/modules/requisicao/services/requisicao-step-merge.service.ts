import { NgForm } from '@angular/forms';
import { Injectable } from "@angular/core";
import { Requisicao } from "src/model/requisicao";

import { StepMergeService } from "../../shared/services/step-merge.service";

@Injectable({
    providedIn: "root",
})
export class RequisicaoStepMergeService extends StepMergeService<Requisicao> {

    private _onSubmit = () => {};

    constructor() {
        super({
            dataRequisicao: new Date(),
            itens: [],
        });
    }

    get onSubmit() {
        return this._onSubmit
    }

    set onSubmit(func) {
        if(!func) {
            func = () => {}
        }
        this._onSubmit = func;
    }
}
