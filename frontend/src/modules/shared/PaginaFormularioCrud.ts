import { NgForm } from '@angular/forms';

export interface PaginaFormularioCrud<T> {
    onSubmit(formulario: NgForm): void;
    onLimpar(): void;
}
