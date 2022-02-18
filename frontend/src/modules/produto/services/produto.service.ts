import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from 'src/model/produto';
import { environment } from 'src/environments/environment';

import { CrudService } from '../../shared/services/crud.service';
import { HistoricoEstoqueProduto } from './../../../model/historico-estoque';


@Injectable({
    providedIn: "root",
})
export class ProdutoService extends CrudService<Produto, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/produtos`);
    }

    buscarHistoricos(idProduto : number) : Observable<HistoricoEstoqueProduto[]> {
        return this._http.get<HistoricoEstoqueProduto[]>(`${this._base}/historicos-estoque/${idProduto}`);
    }
}
