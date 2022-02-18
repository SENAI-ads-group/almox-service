import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

    private _url : string;

  constructor(private _http: HttpClient) {
      this._url = `${environment.api.baseUrl}/common`;
  }

  buscarEnumeradores() : Observable<any>{
    return this._http.get<any>(`${this._url}/enumeradores`)
  }

}
