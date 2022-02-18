import { Auditavel } from "src/app/model/auditavel";
import { Produto } from "./produto";
export interface ItemRequisicao extends Auditavel {
    id?: number;
    statusItemRequisicao?: any;
    quantidade?: number;
    produto?: Produto;
}
