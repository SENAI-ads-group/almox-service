import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators'

export abstract class CrudService<T, ID> {

  constructor(protected _http: HttpClient,protected _base: string) {}

  criar(t: T): Observable<T> {
    return this._http.post<T>(`${this._base}`, t);
  }

  atualizar(id: ID, t: T): Observable<T> {
    return this._http.put<T>(this._base + "/" + id, t, {}).pipe(take(1));
  }

  buscarPorId(id: ID): Observable<T> {
    return this._http.get<T>(this._base + "/" + id);
  }

  buscarTodosFiltrado(filtro : any): Observable<T[]> {
    return this._http.post<T[]>(`${this._base}/listar`, filtro)
  }

  buscarTodos(): Observable<T[]> {
    return this._http.get<T[]>(`${this._base}/listar`)
  }

  excluir(id: ID): Observable<T> {
    return this._http.delete<T>(this._base + '/' + id).pipe(take(1));
  }

}
