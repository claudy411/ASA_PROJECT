import { Mascota } from '../mascotas/mascota';
export class Acogida {

    id?:number=0;
    nombre:string="";
    apellido1:string="";
    apellido2:string="";
    email:string="";
    telefono:string="";
    direccion:string;
    ciudad:string="";
    mascotas:Mascota[];
}
