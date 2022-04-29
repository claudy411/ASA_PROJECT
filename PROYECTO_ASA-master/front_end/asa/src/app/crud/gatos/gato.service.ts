import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError } from 'rxjs';
import { Gato } from './gato';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class GatoService {

  private urlEndPoint: string = 'http://localhost:8080/asa/gatos';
    private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


    constructor(private http: HttpClient, private router: Router) { }

    getGatos(page: number): Observable<any> {

        return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

            map((response: any) => {
                (response.content as Gato[]).map(gato => {
                    gato.nombre = gato.nombre.toUpperCase();
                    return gato;
                });
                return response;
            })

        );
    }

    create(gato: Gato): Observable<any> {

        return this.http.post<Gato>(this.urlEndPoint, gato, { headers: this.httpHeaders }).pipe(
            catchError(e => {

                if (e.status == 400) {
                    throw new Error(e);
                }

                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                throw new Error(e);
            })

        );
    }


    getGato(id): Observable<Gato> {

      return this.http.get<Gato>(`${this.urlEndPoint}/${id}`).pipe(
          catchError(e => {
              this.router.navigate(['/gatos'])
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              throw new Error(e);
          })
      )
  }

    update(gato: Gato): Observable<any> {
        return this.http.put<any>(`${this.urlEndPoint}/${gato.id}`, gato, { headers: this.httpHeaders }).pipe(
            catchError(e => {

                if (e.status == 400) {
                  throw new Error(e);
                }

                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                throw new Error(e);
            })
        );
    }

    delete(id: number): Observable<Gato> {
        return this.http.delete<Gato>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
            catchError(e => {
                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                throw new Error(e);
            })
        );
    }

    subirFoto(archivo: File, id): Observable<Gato> {

        let formData = new FormData();
        formData.append("archivo", archivo);
        formData.append("id", id);

        return this.http.post(`${this.urlEndPoint}/upload`, formData).pipe(
            map((response: any) => response.gato as Gato),
            catchError(e => {
                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                throw new Error(e);
            })

        );
    }
}
