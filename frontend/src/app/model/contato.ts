
import { Auditavel } from "src/app/model/auditavel";

export interface Contato extends Auditavel {
    id?: number;
    email1?: string;
    email2?: string;
    telefone1?: string;
    tipoTelefone1?: any;
    telefone2?: string;
    tipoTelefone2?: any;
    logradouro?: string;
    complemento?: string;
    numero?: string;
    cep?: string;
    cidade?: string;
    estado?: string;
    bairro?: string;
    tipoEndereco?: any;
}
