import { Auditavel } from './auditavel';
import { Produto } from './produto';

export interface Grupo extends Auditavel{
id?: number;
descricao?: string;
produtos?: Produto[];
}
