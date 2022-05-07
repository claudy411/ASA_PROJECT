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


    /*
    
  productoURL = 'http://localhost:8080/producto/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.productoURL + 'lista');
  }

  public detail(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detail/${id}`);
  }

  public detailName(nombre: string): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detailname/${nombre}`);
  }

  public save(producto: Producto): Observable<any> {
    return this.httpClient.post<any>(this.productoURL + 'create', producto);
  }

  public update(id: number, producto: Producto): Observable<any> {
    return this.httpClient.put<any>(this.productoURL + `update/${id}`, producto);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.productoURL + `delete/${id}`);
  }
    */

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

