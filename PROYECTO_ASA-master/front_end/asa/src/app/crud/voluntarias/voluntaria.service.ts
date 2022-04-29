import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError, throwError } from 'rxjs';
import { Voluntaria } from './voluntaria';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class VoluntariaService {

  private urlEndPoint: string = 'http://localhost:8080/asa/voluntarias';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private router: Router) { }

  getVoluntarias(page: number): Observable<any> {

      return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

          map((response: any) => {
              (response.content as Voluntaria[]).map(voluntaria => {
                  voluntaria.nombre = voluntaria.nombre.toUpperCase();
                  return voluntaria;
              });
              return response;
          })

      );
  }

  create(voluntaria: Voluntaria): Observable<any> {

      return this.http.post<Voluntaria>(this.urlEndPoint, voluntaria, { headers: this.httpHeaders }).pipe(
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


  getVoluntaria(id): Observable<Voluntaria> {

      return this.http.get<Voluntaria>(`${this.urlEndPoint}/${id}`).pipe(
          catchError(e => {
              this.router.navigate(['/voluntarias'])
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })
      )
  }

  update(voluntaria: Voluntaria): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${voluntaria.id}`, voluntaria, { headers: this.httpHeaders }).pipe(
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

  delete(id: number): Observable<Voluntaria> {
      return this.http.delete<Voluntaria>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
          catchError(e => {
              console.error(e.error.mensaje);
              Swal.fire(e.error.mensaje, e.error.error, 'error');
              return throwError(e);
          })
      );
  }

  
}

