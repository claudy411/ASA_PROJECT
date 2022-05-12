import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Adoptante } from './adoptante';
import Swal from 'sweetalert2';
import { Mascota } from '../mascotas/mascota';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdoptanteServiceService {

  //version mejicana

  // private url:string =`${environment.HOST}/adoptantes`;

  // constructor(private http: HttpClient) { }

  // listar(page:number):Observable<any> {

  //   return this.http.get<Adoptante[]>(this.url+ '/page/' + page);
  // }
  // listarPorId(id:number):Observable<any>{
  //     return this.http.get<Adoptante>(`${this.url}/${id}`);
  // }

  // registrar(adoptante:Adoptante){
  //     return this.http.post(this.url,adoptante);
  // }

  // modificar(adoptante:Adoptante){
  //     return this.http.put(this.url,adoptante);
  // }

  // eliminar(id:number){
  //     return this.http.delete(`${this.url}/${id}`);
  // }

  // listarMascotas():Observable<Mascota[]>{
  //   return this.http.get<Mascota[]>(`${environment.HOST}/mascotas`);
  // }


  private urlEndPoint: string = 'http://localhost:8080/asa/adoptantes';



    constructor(private http: HttpClient) { }

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

        return this.http.post<Adoptante>(this.urlEndPoint, adoptante);
    }


    getAdoptante(id:any): Observable<Adoptante> {

        return this.http.get<Adoptante>(`${this.urlEndPoint}/${id}`);
    }

    update(adoptante: Adoptante): Observable<any> {
        return this.http.put<any>(`${this.urlEndPoint}/${adoptante.id}`, adoptante);
    }

    delete(id: number): Observable<Adoptante> {
        return this.http.delete<Adoptante>(`${this.urlEndPoint}/${id}`);
    }

    
}

