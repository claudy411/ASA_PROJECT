import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError, throwError } from 'rxjs';
import { Residencia } from './residencia';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ResidenciaService {

  private urlEndPoint: string = 'http://localhost:8080/asa/residencias';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private router: Router) { }

  getResidencias(page: number): Observable<any> {
    //return of(CLIENTES);
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

      map((response: any) => {
        (response.content as Residencia[]).map(residencia => {
          residencia.nombre = residencia.nombre.toUpperCase();
          return residencia;
        });
        return response;
      })

    );
  }

  create(residencia: Residencia): Observable<any> {

    return this.http.post<Residencia>(this.urlEndPoint, residencia, { headers: this.httpHeaders }).pipe(
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


  getResidencia(id): Observable<Residencia> {

    return this.http.get<Residencia>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/residencias'])
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    )
  }

  update(residencia: Residencia): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${residencia.id}`, residencia, { headers: this.httpHeaders }).pipe(
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

  delete(id: number): Observable<Residencia> {
    return this.http.delete<Residencia>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }


}

