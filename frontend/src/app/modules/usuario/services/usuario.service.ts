import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from '../../../../environments/environment';
import { Usuario } from '../../../model/usuario';
import { CrudService } from '../../shared/services/crud.service';

@Injectable({
    providedIn: "root",
})
export class UsuarioService extends CrudService<Usuario, number> {
    constructor(protected _http: HttpClient) {
        super(_http, `${environment.api.baseUrl}/usuarios`);
    }
}
