import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Produto } from 'src/app/model/produto';
import { environment } from 'src/environments/environment';

import { CrudService } from '../../shared/services/crud.service';


@Injectable({
    providedIn: "root",
})
export class ProdutoService extends CrudService<Produto, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/produtos`);
    }
}
