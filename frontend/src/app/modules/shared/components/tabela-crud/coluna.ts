interface Coluna {
    campo: string;
    titulo: string;
    tipo: TipoColuna;
    textAlign: string;
}

enum TipoColuna {
    DATA,
    DATA_HORA,
    TEXTO,
    INTEIRO,
    PERCENTUAL,
    DECIMAL,
    MONETARIO,
    BOOLEANO,
    CPF_CNPJ,
    STATUS_AUDITAVEL
};

function criarConfiguracaoColuna(
    campo: string,
    titulo: string,
    tipo: any,
    textAlign = "left"
): Coluna {
    return { campo, titulo, tipo, textAlign };
}

function criarConfiguracaoColunaStatusAuditavel(titulo: string) {
    return {
        campo: "statusAuditoria.descricao",
        titulo,
        tipo: TipoColuna.STATUS_AUDITAVEL,
        textAlign: "center",
    };
}

export {
    Coluna,
    TipoColuna,
    criarConfiguracaoColuna,
    criarConfiguracaoColunaStatusAuditavel,
};
