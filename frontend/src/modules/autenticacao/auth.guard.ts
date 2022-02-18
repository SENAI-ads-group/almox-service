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
        this.router.navigate(["/login"]);
        return true;
    }
}
