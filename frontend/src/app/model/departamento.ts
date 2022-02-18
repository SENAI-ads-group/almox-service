import { Produto } from './produto';
import { Usuario } from 'src/app/model/usuario';
import { Auditavel } from "./auditavel";
export interface Departamento extends Auditavel {
    id?: number;
    nome?: string;
    usuarios?: Usuario[];
    produtos?: Produto[];
}
