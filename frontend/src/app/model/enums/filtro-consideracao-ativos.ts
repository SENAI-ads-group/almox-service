export const FiltroConsideracaoAtivos = Object.freeze({
    TODOS: {
        type: "CONSIDERAR_TODOS",
        descricao: "Considerar Todos",
        valor: null,
    },
    APENAS_ATIVOS: {
        type: "APENAS_ATIVOS",
        descricao: "Considerar Apenas Ativos",
        valor: true,
    },
    APENAS_EXCLUIDOS: {
        type: "APENAS_EXCLUIDOS",
        descricao: "Considerar Apenas Excluídos",
        valor: false,
    },
    resolverSelecaoConsideracao(consideracao: any) {
        if (consideracao == null) return FiltroConsideracaoAtivos.TODOS;
        if (!!consideracao) return FiltroConsideracaoAtivos.APENAS_ATIVOS;
        if (!consideracao) return FiltroConsideracaoAtivos.APENAS_EXCLUIDOS;
        return undefined;
    },
});
