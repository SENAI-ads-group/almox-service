import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { StepMergeService } from "../../services/step-merge.service";

@Component({
    selector: "step",
    templateUrl: "./step.component.html",
    styleUrls: ["./step.component.scss"],
})
export class StepComponent implements OnInit {
    @Input() titulo: string;
    @Input() subtitulo: string;
    @Input() exibirBotaoAnterior: boolean;
    @Input() exibirBotaoProximo: boolean;
    @Input() exibirBotaoFinalizar: boolean;
    @Input() stepMergeService : StepMergeService<any>;

    @Output("anterior") anteriorEvent = new EventEmitter<any>();
    @Output("proximo") proximoEvent = new EventEmitter<any>();
    @Output("finalizar") finalizarEvent = new EventEmitter<any>();


    constructor() {}

    ngOnInit(): void {}

    onAnterior() {
        this.stepMergeService.stepAnterior();
    }

    onProximo() {
        this.stepMergeService.proximoStep();
    }

    onFinalizar() {
        this.finalizarEvent.emit();
    }
}
