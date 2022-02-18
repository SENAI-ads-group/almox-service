import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../services/login.service';
import { SolicitacaoLogin } from './../../../../model/solicitacao-login';

@Component({
    selector: "login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.scss"],
})
export class LoginComponent {
    solicitacaoLogin: SolicitacaoLogin = {};

    constructor(private loginService: LoginService, private router: Router) {}

    logar() {
        this.loginService
            .logar(this.solicitacaoLogin)
            .subscribe(() => this.router.navigate(["/"]));
    }
}
