import { Message } from "primeng/api";
export class Mensagens {

    static readonly BEM_VINDO: Message = {
        severity: "success",
        key: "notification",
        summary: "Bem vindo(a)",
        life: 1500,
    };

    static readonly SUCESSO_REGISTRO_SALVO: Message = {
        severity: "success",
        key: "notification",
        summary: "Registro Salvo com Sucesso!",
        life: 1500,
    };

    static readonly SUCESSO_REGISTRO_EXCLUIDO: Message = {
        severity: "success",
        key: "notification",
        summary: "Registro Excluído com Sucesso!",
        life: 1500,
    };

    static readonly SUCESSO_PRODUTO_ADICIONADO: Message = {
        severity: "success",
        key: "requisicao-form",
        summary: "Produto Adicionado com Sucesso!",
        life: 1500,
    };

    static readonly SUCESSO_PRODUTO_ATUALIZADO: Message = {
        severity: "success",
        key: "requisicao-form",
        summary: "Produto Atualizado com Sucesso!",
        life: 1500,
    };

    static readonly INFO_REQUISICAO_EM_ATENDIMENTO: Message = {
        severity: "info",
        key: "notification",
        summary: "Atendimento",
        detail: "Iniciado o Atendimento da Requisição",
        life: 1500,
    };

    static readonly SUCESSO_REQUISICAO_ENTREGUE: Message = {
        severity: "success",
        key: "notification",
        summary: "Sucesso",
        detail: "Requisição entregue!",
        life: 1500,
    };

    static readonly SUCESSO_PEDIDO_RECEBIDO: Message = {
        severity: "success",
        key: "notification",
        summary: "Sucesso",
        detail: "Pedido recebido!",
        life: 1500,
    }

    static readonly SUCESSO_PEDIDO_CANCELADO: Message = {
        severity: "success",
        key: "notification",
        summary: "Sucesso",
        detail: "Pedido cancelado!",
        life: 1500,
    }
}
