import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Horario } from './horario';

@Injectable({
  providedIn: 'root'
})
export class HorarioService {

  private url:string =`${environment.HOST}/horarios`;

  constructor(private http: HttpClient) { }

  listar(page:number):Observable<any> {

    return this.http.get<Horario[]>(this.url+ '/page/' + page);
  }
  listarPorId(id:number):Observable<any>{
      return this.http.get<Horario>(`${this.url}/${id}`);
  }

  registrar(horario:Horario){
      return this.http.post(this.url,horario);
  }

  modificar(horario:Horario){
      return this.http.put(this.url,horario);
  }

  eliminar(id:number){
      return this.http.delete(`${this.url}/${id}`);
  }
}
