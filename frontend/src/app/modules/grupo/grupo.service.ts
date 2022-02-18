import { Grupo } from './../../model/grupo';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CrudService } from "../shared/services/crud.service";

import { environment } from "../../../environments/environment";

@Injectable({
    providedIn: "root",
})
export class GrupoService extends CrudService<Grupo, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/grupos`);
    }
}
