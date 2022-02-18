import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Fornecedor } from "src/app/model/fornecedor";
import { environment } from "src/environments/environment";
import { CrudService } from "../../shared/services/crud.service";

@Injectable({
    providedIn: "root",
})
export class FornecedorService extends CrudService<Fornecedor, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/fornecedores`);
    }
}
