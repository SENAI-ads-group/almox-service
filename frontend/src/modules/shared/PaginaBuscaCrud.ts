import { Component, OnInit } from "@angular/core";
import { CrudService } from "./services/crud.service";

@Component({
    template: "",
})
export abstract class PaginaBuscaCrud<T> implements OnInit {
    registros: T[];
    loading: boolean;

    constructor(protected service: CrudService<T, any>) {}

    abstract ngOnInit(): void;
    abstract onBuscar(filtro: any): void;
    abstract onEditar(registro: T): void;
    abstract onExcluir(registro: T): void;
}
