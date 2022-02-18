import { Produto } from './produto';
export interface ConfiguracaoEstoque {
    id?: number;
    estoqueMinimo?: number;
    estoqueAtual?: number;
    estoqueMaximo?: number;
    controlaEstoqueMinimo?: boolean;
    controlaEstoqueMaximo?: boolean;
    produto?: Produto;
}
