import { Observable } from 'rxjs';
import { ProdutoService } from "./../../services/produto.service";
import { Component, OnInit } from "@angular/core";
import { Produto } from "src/app/model/produto";
import { DynamicDialogConfig, DynamicDialogRef } from "primeng/dynamicdialog";

@Component({
    selector: "produto-modal-lista",
    templateUrl: "./produto-modal-lista.component.html",
})
export class ProdutoModalListaComponent implements OnInit {
    produtos$ : Observable<Produto[]>;

    constructor(
        private produtoService: ProdutoService,
        public dynamicDialogRef: DynamicDialogRef,
        public configDialog: DynamicDialogConfig
    ) {}

    ngOnInit(): void {
        this.produtos$ = this.produtoService.buscarTodos();
    }

    onSelecionar(event: MouseEvent, produtoSelecionado: Produto) {
        this.dynamicDialogRef.close(produtoSelecionado);
    }

}
