import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError, throwError } from 'rxjs';
import { Localizacion } from './localizacion';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LocalizacionService {

  private urlEndPoint: string = 'http://localhost:8080/asa/localizaciones';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private router: Router) { }

  getLocalizaciones(page: number): Observable<any> {

      return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

          map((response: any) => {
              (response.content as Localizacion[]).map(localizacion => {
                  localizacion.nombre = localizacion.nombre.toUpperCase();
                  return localizacion;
              });
              return response;
          })

      );
  }

  create(localizacion: Localizacion): Observable<any> {

      return this.http.post<Localizacion>(this.urlEndPoint, localizacion, { headers: this.httpHeaders }).pipe(
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


  getLocalizacion(id): Observable<Localizacion> {

      return this.http.get<Localizacion>(`${this.urlEndPoint}/${id}`).pipe(
          catchError(e => {
              this.router.navigate(['/localizacions'])
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })
      )
  }

  update(localizacion: Localizacion): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${localizacion.id}`, localizacion, { headers: this.httpHeaders }).pipe(
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

  delete(id: number): Observable<Localizacion> {
      return this.http.delete<Localizacion>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
          catchError(e => {
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })
      );
  }
}