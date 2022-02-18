import { Router } from '@angular/router';
import { Component, OnInit } from "@angular/core";
import { MenuItem } from "primeng/api";
import { environment } from "src/environments/environment";

import { MainComponent } from "../main/main.component";
import { Navigation } from "./../../../config/navigation";

@Component({
    selector: "barra-superior",
    templateUrl: "./barra-superior.component.html",
})
export class BarraSuperiorComponent implements OnInit {
    items: MenuItem[];

    constructor(public appMain: MainComponent, private router : Router) {}

    ngOnInit(): void {
        this.items = Navigation;
    }

    logout() {
        sessionStorage.setItem(
            environment.auth.tokensessionStorage,
            null
        );
        this.router.navigate(['/login']);
    }
}
