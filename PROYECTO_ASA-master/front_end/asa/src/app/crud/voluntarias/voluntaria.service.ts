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
 

  constructor(private http: HttpClient) { }

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

      return this.http.post<Voluntaria>(this.urlEndPoint, voluntaria);
  }


  getVoluntaria(id:any): Observable<Voluntaria> {

      return this.http.get<Voluntaria>(`${this.urlEndPoint}/${id}`);
  }

  update(voluntaria: Voluntaria): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${voluntaria.id}`, voluntaria);
  }

  delete(id: number): Observable<Voluntaria> {
      return this.http.delete<Voluntaria>(`${this.urlEndPoint}/${id}`);
  }
}