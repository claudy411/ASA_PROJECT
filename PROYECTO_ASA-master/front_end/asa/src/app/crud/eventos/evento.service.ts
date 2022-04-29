import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError, throwError } from 'rxjs';
import { Evento } from './evento';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class EventoService {

  private urlEndPoint: string = 'http://localhost:8080/asa/eventos';
    private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  
    constructor(private http: HttpClient, private router: Router) { }
  
    getEventos(page: number): Observable<any> {
     
      return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
  
        map((response: any) => {
          (response.content as Evento[]).map(evento => {
            evento.nombre = evento.nombre.toUpperCase();
            return evento;
          });
          return response;
        })
  
      );
    }
  
    create(evento: Evento): Observable<any> {
  
      return this.http.post<Evento>(this.urlEndPoint, evento, { headers: this.httpHeaders }).pipe(
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
  
  
    getEvento(id): Observable<Evento> {
  
      return this.http.get<Evento>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e => {
          this.router.navigate(['/eventos'])
          console.error(e.error.mensaje);
          Swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError(e);
        })
      )
    }
  
    update(evento: Evento): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${evento.id}`, evento, { headers: this.httpHeaders }).pipe(
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
  
    delete(id: number): Observable<Evento> {
      return this.http.delete<Evento>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
        catchError(e => {
          console.error(e.error.mensaje);
          Swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError(e);
        })
      );
    }
  
  
  }
