import { environment } from "./../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class LoginService {
    constructor(private http: HttpClient) {}

    public logar(username, password): Observable<any> {
        return this.http.post(`${environment.api.baseUrl}/auth`, {
            usuario: username,
            senha: password,
        });
    }
}
