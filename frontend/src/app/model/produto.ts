import { Auditavel } from './auditavel';
import { ConfiguracaoEstoque } from './configuracao-estoque';
import { Departamento } from './departamento';
import { Fabricante } from './fabricante';
import { Fornecedor } from './fornecedor';
import { Grupo } from './grupo';

export interface Produto extends Auditavel {
    id?: number;
    descricao?: string;
    codigoBarras?: string;
    unidadeMedida?: any;
    palavrasChave?: string[];
    possuiLoteValidade?: boolean;
    custoMedio?: number;
    fabricante?: Fabricante;
    fornecedores?: Fornecedor[];
    detalhes?: string;
    departamentos?: Departamento[];
    grupo?: Grupo;
    configuracaoEstoque?: ConfiguracaoEstoque;
}
