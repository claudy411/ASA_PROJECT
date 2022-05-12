import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, catchError, throwError } from 'rxjs';
import { Residencia } from './residencia';
import Swal from 'sweetalert2';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResidenciaService {

 // private urlEndPoint: string = 'http://localhost:8080/asa/residencias';
 
  private url:string =`${environment.HOST}/residencias`;

  constructor(private http: HttpClient) { }

  listar(page:number):Observable<any> {

    return this.http.get<Residencia[]>(this.url+ '/page/' + page);
  }
  listarPorId(id:number):Observable<any>{
      return this.http.get<Residencia>(`${this.url}/${id}`);
  }

  registrar(residencia:Residencia){
      return this.http.post(this.url,residencia);
  }

  modificar(residencia:Residencia){
      return this.http.put(this.url,residencia);
  }

  eliminar(id:number){
      return this.http.delete(`${this.url}/${id}`);
  }

//   getResidencias(page: number): Observable<any> {

//       return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

//           map((response: any) => {
//               (response.content as Residencia[]).map(residencia => {
//                   residencia.nombre = residencia.nombre.toUpperCase();
//                   return residencia;
//               });
//               return response;
//           })

//       );
//   }

//   create(residencia: Residencia): Observable<any> {

//       return this.http.post<Residencia>(this.urlEndPoint, residencia);
//   }


//   getResidencia(id:any): Observable<Residencia> {

//       return this.http.get<Residencia>(`${this.urlEndPoint}/${id}`);
//   }

//   update(residencia: Residencia): Observable<any> {
//       return this.http.put<any>(`${this.urlEndPoint}/${residencia.id}`, residencia);
//   }

//   delete(id: number): Observable<Residencia> {
//       return this.http.delete<Residencia>(`${this.urlEndPoint}/${id}`);
//   }
}

