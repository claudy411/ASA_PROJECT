import { Encargado } from '../encargados-localizaciones/encargado';
import { Evento } from '../eventos/evento';
export class Localizacion {
    id:number;
    nombre:string;
direccion:string;
    localidad:string;
    encargados:Encargado[];
    evento:Evento;
}
