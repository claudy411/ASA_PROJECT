import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Adoptante } from './adoptante';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AdoptanteServiceService {

  private urlEndPoint: string = 'http://localhost:8080/asa/adoptantes';
    private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


    constructor(private http: HttpClient, private router: Router) { }

    getAdoptantes(page: number): Observable<any> {

        return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

            map((response: any) => {
                (response.content as Adoptante[]).map(adoptante => {
                    adoptante.nombre = adoptante.nombre.toUpperCase();
                    return adoptante;
                });
                return response;
            })

        );
    }

    create(adoptante: Adoptante): Observable<any> {

        return this.http.post<Adoptante>(this.urlEndPoint, adoptante, { headers: this.httpHeaders }).pipe(
            catchError(e => {

                if (e.status == 400) {
                    return throwError(e);
                }

                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                return throwError(e);
            })

        );
    }


    getAdoptante(id:any): Observable<Adoptante> {

        return this.http.get<Adoptante>(`${this.urlEndPoint}/${id}`).pipe(
            catchError(e => {
                this.router.navigate(['/adoptantes'])
                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                return throwError(e);
            })
        )
    }

    update(adoptante: Adoptante): Observable<any> {
        return this.http.put<any>(`${this.urlEndPoint}/${adoptante.id}`, adoptante, { headers: this.httpHeaders }).pipe(
            catchError(e => {

                if (e.status == 400) {
                    return throwError(e);
                }

                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                return throwError(e);
            })
        );
    }

    delete(id: number): Observable<Adoptante> {
        return this.http.delete<Adoptante>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
            catchError(e => {
                console.error(e.error.mensaje);
                Swal.fire(e.error.mensaje, e.error.error, 'error');
                return throwError(e);
            })
        );
    }

    
}

