import { Contato } from './contato';
import { PessoaJurica } from "./pessoa-juridica";

export interface Fabricante extends PessoaJurica {
    id?: number;
    razaoSocial?:string;
    nomeFantasia?:string;
    rg?:string;
    cnpj?:string;
    contato?:Contato;
}
