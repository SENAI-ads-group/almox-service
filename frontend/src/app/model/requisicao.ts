import { Usuario } from "src/app/model/usuario";
import { Departamento } from "./departamento";
import { ItemRequisicao } from "./item-requisicao";
export interface Requisicao {
    produto?: any;
    id?: number;
    dataRequisicao?: Date;
    requisitante?: Usuario;
    almoxarife?: Usuario;
    departamento?: Departamento;
    status?: any;
    itens?: ItemRequisicao[];
}
