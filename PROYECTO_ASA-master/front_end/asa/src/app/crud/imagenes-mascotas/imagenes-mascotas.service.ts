import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ImagenesMascotas } from './imagenes-mascotas';
import { Mascota } from '../mascotas/mascota';

@Injectable({
  providedIn: 'root'
})
export class ImagenesMascotasService {

  private url:string =`${environment.HOST}/imagenes-mascotas`;

  constructor(private http: HttpClient) { }

  listar(page:number):Observable<any> {

    return this.http.get<ImagenesMascotas[]>(this.url+ '/page/' + page);
  }
  listarPorId(id:number):Observable<any>{
      return this.http.get<ImagenesMascotas>(`${this.url}/${id}`);
  }

  registrar(img:ImagenesMascotas){
      return this.http.post(this.url,img);
  }

  modificar(img:ImagenesMascotas){
      return this.http.put(this.url,img);
  }

  eliminar(id:number){
      return this.http.delete(`${this.url}/${id}`);
  }

  listarMascotas():Observable<Mascota[]>{//esto me chirria mucho
    return this.http.get<Mascota[]>(`${environment.HOST}/mascotas`);
  }
}
