import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { Observable, map, catchError, throwError } from 'rxjs';
import { Evento } from './evento';
import { environment } from 'src/environments/environment';
import { Localizacion } from '../localizaciones/localizacion';
 

@Injectable({
  providedIn: 'root'
})
export class EventoService {

    //version mejicana

    // private url:string =`${environment.HOST}/eventos`;

    // constructor(private http: HttpClient) { }
  
    // listar(page:number):Observable<any> {
  
    //   return this.http.get<Evento[]>(this.url+ '/page/' + page);
    // }
    // listarPorId(id:number):Observable<any>{
    //     return this.http.get<Evento>(`${this.url}/${id}`);
    // }
  
    // registrar(evento:Evento){
    //     return this.http.post(this.url,evento);
    // }
  
    // modificar(evento:Evento){
    //     return this.http.put(this.url,evento);
    // }
  
    // eliminar(id:number){
    //     return this.http.delete(`${this.url}/${id}`);
    // }
  
    // listarLocalizaciones():Observable<Localizacion[]>{//esto me chirria mucho
    //   return this.http.get<Localizacion[]>(`${environment.HOST}/localizaciones`);
    // }




  private urlEndPoint: string = 'http://localhost:8080/asa/eventos';

  constructor(private http: HttpClient) { }

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

      return this.http.post<Evento>(this.urlEndPoint, evento);
  }


  getEvento(id:any): Observable<Evento> {

      return this.http.get<Evento>(`${this.urlEndPoint}/${id}`);
  }

  update(evento: Evento): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${evento.id}`, evento);
  }

  delete(id: number): Observable<Evento> {
      return this.http.delete<Evento>(`${this.urlEndPoint}/${id}`);
  }
}