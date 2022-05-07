import { Injectable } from '@angular/core';
import {  HttpClient } from '@angular/common/http';

import { Observable, map, catchError } from 'rxjs';
import { Gato } from './gato';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class GatoService {

    private urlEndPoint: string = 'http://localhost:8080/asa/gatos';

    constructor(private http: HttpClient) { }
  
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
  
        return this.http.post<Gato>(this.urlEndPoint, gato);
    }
  
  
    getGato(id:any): Observable<Gato> {
  
        return this.http.get<Gato>(`${this.urlEndPoint}/${id}`);
    }
  
    update(gato: Gato): Observable<any> {
        return this.http.put<any>(`${this.urlEndPoint}/${gato.id}`, gato);
    }
  
    delete(id: number): Observable<Gato> {
        return this.http.delete<Gato>(`${this.urlEndPoint}/${id}`);
    }
  }