import { Mascota } from '../mascotas/mascota';


export class Adoptante {
    
    id?:number=0;
    nombre:string="";
    apellido1:string="";
    apellido2:string="";
    email:string="";
    telefono:string="";
    direccion:string;
    ciudad:string="";
    mascotas:Mascota[];

    // constructor(nombre:string,apellido1:string,apellido2:string,email:string,telefono:string,ciudad:string){
    //     this.nombre=nombre;
    //     this.apellido1=apellido1;
    //     this.apellido2=apellido2;
    //     this.email=email;
    //     this.telefono=telefono;
    //     this.ciudad=ciudad;
    // }
}
