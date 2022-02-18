import { ActivatedRouteSnapshot } from "@angular/router";

const rotaEstaEmModoVisualizacao = (activatedRoute: ActivatedRouteSnapshot) => {
    if (!activatedRoute) return false;

    const [{ path }] = activatedRoute.url;
    return path && path === "visualizar";
};

export { rotaEstaEmModoVisualizacao };
