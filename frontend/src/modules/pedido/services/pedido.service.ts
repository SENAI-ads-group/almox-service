import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Requisicao } from "src/model/requisicao";
import { environment } from "src/environments/environment";
import { CrudService } from "../../shared/services/crud.service";
import { Pedido } from "src/model/pedido";

@Injectable({
    providedIn: "root",
})
export class PedidoService extends CrudService<Pedido, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/pedidos`);
    }

    cancelarPedido(id: number): Observable<any> {
        return this._http.post(this._base + "/cancelar/" + id, null);
    }

    receberPedido(pedido: Pedido): Observable<any> {
        return this._http.post(
            this._base + "/receber/" + pedido.id,
            pedido
        );
    }
}
