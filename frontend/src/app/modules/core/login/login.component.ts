import { Mensagens } from 'src/app/utils/Mensagens';
import { MessageService } from 'primeng/api';
import { Component } from "@angular/core";
import { Router } from "@angular/router";

import { LoginService } from "./../services/login.service";

@Component({
    selector: "login",
    templateUrl: "./login.component.html",
})
export class LoginComponent {
    username: string;
    password: string;

    constructor(private loginService: LoginService, private router: Router, private messageService : MessageService) {}

    login() {
        this.loginService.logar(this.username, this.password).subscribe(r => {
            sessionStorage.setItem("almox_access_token", r.access_token);
            this.router.navigate(["/"]);
            this.messageService.add(Mensagens.BEM_VINDO)
        }, () => {console.log('er')});
    }
}
