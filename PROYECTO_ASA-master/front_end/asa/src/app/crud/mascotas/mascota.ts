import { Adoptante } from '../adoptantes/adoptante';
import { Acogida } from '../acogidas/acogida';
import { ImagenesMascotas } from '../imagenes-mascotas/imagenes-mascotas';
import { Residencia } from '../residencias/residencia';

export class Mascota {

    id?:number;
    nombre:string;
    fNacimiento:Date;
    fEntrada:Date;
    tipo:string;//enumerado
    size:string;//enumerado
    caracter:string;
    raza:string;
    situacion:string;//enumerado
    adoptantes:Adoptante[];
    acogidas:Acogida[];
    fotos:ImagenesMascotas[];
    residencia:Residencia;

}
