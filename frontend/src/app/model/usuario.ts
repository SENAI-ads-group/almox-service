import { Departamento } from "./departamento"
import { Auditavel } from "./auditavel";

export interface Usuario extends Auditavel {
    id?: number;
    username?: string;
    email?: string;
    tipoUsuario?: any;
    departamentos?: Departamento[];
}
