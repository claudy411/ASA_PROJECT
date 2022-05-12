import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Acogida } from './acogida';
import { Mascota } from '../mascotas/mascota';

@Injectable({
  providedIn: 'root'
})
export class AcogidaService {

  private url:string =`${environment.HOST}/acogidas`;

  constructor(private http: HttpClient) { }

  listar(page:number):Observable<any> {

    return this.http.get<Acogida[]>(this.url+ '/page/' + page);
  }
  listarPorId(id:number):Observable<any>{
      return this.http.get<Acogida>(`${this.url}/${id}`);
  }

  registrar(acogida:Acogida){
      return this.http.post(this.url,acogida);
  }

  modificar(acogida:Acogida){
      return this.http.put(this.url,acogida);
  }

  eliminar(id:number){
      return this.http.delete(`${this.url}/${id}`);
  }

  listarMascotas():Observable<Mascota[]>{//esto me chirria mucho
    return this.http.get<Mascota[]>(`${environment.HOST}/mascotas`);
  }
}
