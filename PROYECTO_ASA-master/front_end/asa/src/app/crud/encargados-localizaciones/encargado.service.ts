import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { Observable, map, catchError, throwError } from 'rxjs';
import { Encargado } from './encargado';


@Injectable({
  providedIn: 'root'
})
export class EncargadoService {
  
  private urlEndPoint: string = 'http://localhost:8080/asa/encargados';



  constructor(private http: HttpClient) { }

  getEncargados(page: number): Observable<any> {

      return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

          map((response: any) => {
              (response.content as Encargado[]).map(encargado => {
                  encargado.nombre = encargado.nombre.toUpperCase();
                  return encargado;
              });
              return response;
          })

      );
  }

  create(encargado: Encargado): Observable<any> {

      return this.http.post<Encargado>(this.urlEndPoint, encargado);
  }


  getencargado(id:any): Observable<Encargado> {

      return this.http.get<Encargado>(`${this.urlEndPoint}/${id}`);
  }

  update(encargado: Encargado): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${encargado.id}`, encargado);
  }

  delete(id: number): Observable<Encargado> {
      return this.http.delete<Encargado>(`${this.urlEndPoint}/${id}`);
  }


//   private urlEndPoint: string = 'http://localhost:8080/asa/encargados';
//   private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


//   constructor(private http: HttpClient, private router: Router) { }

//   getEncargados(page: number): Observable<any> {

//       return this.http.get(this.urlEndPoint + '/page/' + page).pipe(

//           map((response: any) => {
//               (response.content as Encargado[]).map(encargado => {
//                   encargado.nombre = encargado.nombre.toUpperCase();
//                   return encargado;
//               });
//               return response;
//           })

//       );
//   }

//   create(encargado: Encargado): Observable<any> {

//       return this.http.post<Encargado>(this.urlEndPoint, encargado, { headers: this.httpHeaders }).pipe(
//           catchError(e => {

//               if (e.status == 400) {
//                   return throwError(e);
//               }

//               console.error(e.error.mensaje);
//               Swal.fire(e.error.mensaje, e.error.error, 'error');
//               return throwError(e);
//           })

//       );
//   }


//   getEncargado(id): Observable<Encargado> {

//       return this.http.get<Encargado>(`${this.urlEndPoint}/${id}`).pipe(
//           catchError(e => {
//               this.router.navigate(['/encargados'])
//               console.error(e.error.mensaje);
//               Swal.fire(e.error.mensaje, e.error.error, 'error');
//               return throwError(e);
//           })
//       )
//   }

//   update(encargado: Encargado): Observable<any> {
//       return this.http.put<any>(`${this.urlEndPoint}/${encargado.id}`, encargado, { headers: this.httpHeaders }).pipe(
//           catchError(e => {

//               if (e.status == 400) {
//                   return throwError(e);
//               }

//               console.error(e.error.mensaje);
//               Swal.fire(e.error.mensaje, e.error.error, 'error');
//               return throwError(e);
//           })
//       );
//   }

//   delete(id: number): Observable<Encargado> {
//       return this.http.delete<Encargado>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
//           catchError(e => {
//               console.error(e.error.mensaje);
//               Swal.fire(e.error.mensaje, e.error.error, 'error');
//               return throwError(e);
//           })
//       );
//   }

  
}