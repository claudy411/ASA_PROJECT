import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mascota } from './mascota';

@Injectable({
  providedIn: 'root'
})
export class MascotasService {

  private url:string =`${environment.HOST}/imagenes-mascotas`;

  constructor(private http: HttpClient) { }

  listar(page:number):Observable<any> {

    return this.http.get<Mascota[]>(this.url+ '/page/' + page);
  }
  listarPorId(id:number):Observable<any>{
      return this.http.get<Mascota>(`${this.url}/${id}`);
  }

  registrar(pet:Mascota){
      return this.http.post(this.url,pet);
  }

  modificar(pet:Mascota){
      return this.http.put(this.url,pet);
  }

  eliminar(id:number){
      return this.http.delete(`${this.url}/${id}`);
  }
}
