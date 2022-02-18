import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { environment } from "../../../environments/environment";
import { Departamento } from "../../../model/departamento";
import { CrudService } from "../../shared/services/crud.service";

@Injectable({
    providedIn: "root",
})
export class DepartamentoService extends CrudService<Departamento, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/departamentos`);
    }

    buscarAssociadosUsuarioLogado(): Observable<Departamento[]> {
        return this._http.get<Departamento[]>(
            this._base + "/associados-usuario-logado"
        );
    }

    buscarPorRelacaoProduto(idProduto: number, relacionados : boolean): Observable<Departamento[]> {
        return this._http.get<Departamento[]>(
            this._base + `/relacao-produto/${idProduto}?relacionados=${relacionados}`
        );
    }
}
