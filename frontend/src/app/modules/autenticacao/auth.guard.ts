import { Injectable } from "@angular/core";
import {
    ActivatedRouteSnapshot,
    CanActivate,
    Router,
    RouterStateSnapshot,
} from "@angular/router";

@Injectable({
    providedIn: "root",
})
export class Authguard implements CanActivate {
    constructor(private router: Router) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): boolean {
        const accessToken = sessionStorage.getItem("almox_access_token");
        if (accessToken && accessToken !== 'null') {
            return true;
        }
<<<<<<< HEAD:src/app/config/auth/authguard.service.ts
        //this.router.navigate(["/login"]);
=======
        this.router.navigate(["/login"]);
>>>>>>> a7d33ac6e034c748fdf869da03c3fbb32b236fec:src/app/modules/autenticacao/auth.guard.ts
        return true;
    }
}
