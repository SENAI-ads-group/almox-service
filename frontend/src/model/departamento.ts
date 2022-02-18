import { Produto } from './produto';
import { Usuario } from 'src/model/usuario';
import { Auditavel } from "./auditavel";
export interface Departamento extends Auditavel {
    id?: number;
    nome?: string;
    usuarios?: Usuario[];
    produtos?: Produto[];
}
