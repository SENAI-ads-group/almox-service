import { Injectable } from '@angular/core';
import { Pedido } from 'src/model/pedido';

import { StepMergeService } from '../../shared/services/step-merge.service';

@Injectable({
    providedIn: "root",
})
export class PedidoStepMergeService extends StepMergeService<Pedido> {


    constructor() {
        super({
            dataPedido: new Date(),
            itens: [],
        });
    }

}
