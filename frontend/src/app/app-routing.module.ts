import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

const routes: Routes = [
    { path: "", pathMatch: "full", redirectTo: "/" },
    {
        path: "",
        loadChildren: () =>
            import("./modules/core/core.module").then(
                module => module.CoreModule
            ),
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { scrollPositionRestoration: "enabled" }),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule {}
