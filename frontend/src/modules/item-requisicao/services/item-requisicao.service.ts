import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { ItemRequisicao } from './../../../model/item-requisicao';
import { Injectable } from "@angular/core";
import { CrudService } from '../../shared/services/crud.service';

@Injectable({
    providedIn: "root",
})
export class ItemRequisicaoService extends CrudService<ItemRequisicao, number> {
    constructor(protected _http: HttpClient){
        super(_http, `${environment.api.baseUrl}/item-requisicoes`);
    }

}
