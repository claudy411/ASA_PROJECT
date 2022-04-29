import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError, throwError } from 'rxjs';
import { Residencia } from '../residencias/residencia';
import { Perro } from './perro';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class PerroService {

  private urlEndPoint: string = 'http://localhost:8080/asa/perros';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private router: Router) { }

  getResidencias():Observable<Residencia[]>{
      return this.http.get<Residencia[]>('http://localhost:8080/asa/residencias');
  }



  getPerros(page: number): Observable<any> {

      return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

          map((response: any) => {
              (response.content as Perro[]).map(perro => {
                  perro.nombre = perro.nombre.toUpperCase();
                  return perro;
              });
              return response;
          })

      );
  }

  create(perro: Perro): Observable<any> {

      return this.http.post<Perro>(this.urlEndPoint, perro, { headers: this.httpHeaders }).pipe(
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


  getPerro(id): Observable<Perro> {

      return this.http.get<Perro>(`${this.urlEndPoint}/${id}`).pipe(
          catchError(e => {
              this.router.navigate(['/perros'])
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })
      )
  }

  update(perro: Perro): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${perro.id}`, perro, { headers: this.httpHeaders }).pipe(
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

  delete(id: number): Observable<Perro> {
      return this.http.delete<Perro>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
          catchError(e => {
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })
      );
  }

  subirFoto(archivo: File, id): Observable<Perro> {

      let formData = new FormData();
      formData.append("archivo", archivo);
      formData.append("id", id);

      return this.http.post(`${this.urlEndPoint}/upload`, formData).pipe(
          map((response: any) => response.perro as Perro),
          catchError(e => {
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })

      );
  }
}



