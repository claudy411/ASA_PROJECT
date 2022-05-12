import { Localizacion } from '../localizaciones/localizacion';
import { Voluntaria } from '../voluntarias/voluntaria';
export class Evento {
    
    id:number;
    nombre:string;
    fecha: string;
    descripcion:string;
    localizacion:Localizacion;
    voluntarias: Voluntaria[];
}
