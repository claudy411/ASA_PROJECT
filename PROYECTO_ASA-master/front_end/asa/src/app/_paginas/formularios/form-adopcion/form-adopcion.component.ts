import { Component, OnInit } from '@angular/core';
import { Adoptante } from '../../../crud/adoptantes/adoptante';

@Component({
  selector: 'app-form-adopcion',
  templateUrl: './form-adopcion.component.html',
  styleUrls: ['./form-adopcion.component.css']
})
export class FormAdopcionComponent implements OnInit {

  adoptante: Adoptante = new Adoptante();
  titulo: string = 'Solicitud adopcion'; 
  subtitulo:string;
 errores: string[];

  constructor() { }

  ngOnInit(): void {
    this.subtitulo="Este cuestionario únicamente tiene como finalidad conocer tu estilo de vida y, de ese modo, poder valorar las características que debería tener el perro para lograr una convivencia perfecta en tu hogar."
  }

  enviar(){
    //para enviar por mail la info
  }
}
