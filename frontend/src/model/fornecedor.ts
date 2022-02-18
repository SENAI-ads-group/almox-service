import { PessoaJurica } from './pessoa-juridica';
import { Contato } from './contato';

export interface Fornecedor extends PessoaJurica{
    id?: number;
    razaoSocial?:string;
    nomeFantasia?:string;
    rg?:string;
    cnpj?:string;
    contato?:Contato;

}
