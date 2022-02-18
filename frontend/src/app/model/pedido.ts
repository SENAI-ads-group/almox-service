import { ItemPedido } from './item-pedido';
import { Usuario } from 'src/app/model/usuario';

import { Departamento } from './departamento';
import { Fornecedor } from './fornecedor';
import { ItemRequisicao } from './item-requisicao';

export interface Pedido {
    id?: number;
    dataPedido?: Date;
    dataPrevisaoEntrega?: Date;
    anotacoes?: string;
    fornecedor?: Fornecedor;
    comprador?: Usuario;
    status?: any;
    itens?: ItemPedido[];
}
