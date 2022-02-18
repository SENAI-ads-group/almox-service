import { MessageService } from "primeng/api";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: "root",
})
export class HandleErrorService {
    constructor(private messageService: MessageService) {}

    handleError(erro) {
        if(!erro || !erro.error || !erro.messages) {
            this.messageService.add({severity: 'error', summary: "Erro inesperado!", life: 5000});
            return;
        };
        const { mensagem, mensagens } = erro.error;
        for(const message of mensagens) {
            this.messageService.add({severity: 'error', summary: mensagem, detail: message, life: 5000});
        }
    }
}
