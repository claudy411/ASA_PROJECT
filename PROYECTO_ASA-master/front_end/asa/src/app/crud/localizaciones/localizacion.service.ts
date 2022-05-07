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



    constructor(private http: HttpClient) { }

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

        return this.http.post<Localizacion>(this.urlEndPoint, localizacion);
    }


    getLocalizacion(id:any): Observable<Localizacion> {

        return this.http.get<Localizacion>(`${this.urlEndPoint}/${id}`);
    }

    update(localizacion: Localizacion): Observable<any> {
        return this.http.put<any>(`${this.urlEndPoint}/${localizacion.id}`, localizacion);
    }

    delete(id: number): Observable<Localizacion> {
        return this.http.delete<Localizacion>(`${this.urlEndPoint}/${id}`);
    }
}