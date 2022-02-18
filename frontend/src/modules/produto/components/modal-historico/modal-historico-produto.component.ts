import { HistoricoEstoqueProduto } from "./../../../../model/historico-estoque";
import { Observable } from "rxjs";
import { ProdutoService } from "src/modules/produto/services/produto.service";
import { Component, Input, OnInit } from "@angular/core";
import { DynamicDialogConfig, DynamicDialogRef } from "primeng/dynamicdialog";
import { Produto } from "src/model/produto";
import {
    criarConfiguracaoColuna,
    TipoColuna,
} from "src/modules/shared/components/tabela-crud/coluna";

@Component({
    selector: "modal-historico",
    templateUrl: "./modal-historico-produto.component.html",
    styleUrls: ["./modal-historico-produto.component.scss"],
})
export class ModalHistoricoComponent implements OnInit {
    @Input() produto: Produto;
    colunas: any[];
    registros: HistoricoEstoqueProduto[];
    loading: boolean = false;

    constructor(
        public dynamicDialogRef: DynamicDialogRef,
        public configDialog: DynamicDialogConfig,
        private produtoService: ProdutoService
    ) {}

    ngOnInit(): void {
        this.loading = true;
        this.colunas = [
            criarConfiguracaoColuna(
                "dataRegistro",
                "Data",
                TipoColuna.DATA_HORA,
                "left"
            ),
            criarConfiguracaoColuna(
                "itemMovimento.tipoDeMovimento.descricao",
                "Tipo",
                TipoColuna.TEXTO,
                "left"
            ),
            criarConfiguracaoColuna(
                "itemMovimento.idOrigem",
                "ID Origem",
                TipoColuna.TEXTO,
                "left"
            ),
            criarConfiguracaoColuna(
                "itemMovimento.tipoOrigemMovimento.descricao",
                "Tipo Origem",
                TipoColuna.TEXTO,
                "left"
            ),
            criarConfiguracaoColuna(
                "estoqueAnterior",
                "Estoque Anterior",
                TipoColuna.DECIMAL,
                "left"
            ),
            criarConfiguracaoColuna(
                "itemMovimento.quantidade",
                "Quantidade",
                TipoColuna.DECIMAL,
                "left"
            ),
            criarConfiguracaoColuna(
                "estoqueFinal",
                "Estoque Final",
                TipoColuna.DECIMAL,
                "left"
            ),
            criarConfiguracaoColuna(
                "usuario.nome",
                "Usuário",
                TipoColuna.TEXTO,
                "left"
            ),
        ];
        this.produto = this.configDialog.data;
        this.produtoService
            .buscarHistoricos(this.configDialog.data.id)
            .subscribe(
                dados => {
                    this.registros = dados;
                    this.loading = false;
                },
                () => (this.loading = false)
            );
    }

}
