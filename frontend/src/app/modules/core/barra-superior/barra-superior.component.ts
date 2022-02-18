import { Component, OnInit } from "@angular/core";
import { MenuItem } from "primeng/api";

import { MainComponent } from "../main/main.component";
import { Navigation } from "./../../../config/navigation";

@Component({
    selector: "barra-superior",
    templateUrl: "./barra-superior.component.html",
})
export class BarraSuperiorComponent implements OnInit {
    items: MenuItem[];

    constructor(public appMain: MainComponent) {}

    ngOnInit(): void {
        this.items = Navigation;
    }
}
