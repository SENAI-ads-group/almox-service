import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Requisicao } from "src/app/model/requisicao";
import { environment } from "src/environments/environment";
import { CrudService } from "../../shared/services/crud.service";

@Injectable({
    providedIn: "root",
})
export class RequisicaoService extends CrudService<Requisicao, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/requisicoes`);
    }

    atenderRequisicao(id: number): Observable<any> {
        return this._http.post(this._base + "/atender/" + id, null);
    }

    cancelarRequisicao(id: number): Observable<any> {
        return this._http.post(this._base + "/cancelar/" + id, null);
    }

    entregarRequisicao(requisicao: Requisicao): Observable<any> {
        return this._http.post(
            this._base + "/entregar/" + requisicao.id,
            requisicao
        );
    }
}
