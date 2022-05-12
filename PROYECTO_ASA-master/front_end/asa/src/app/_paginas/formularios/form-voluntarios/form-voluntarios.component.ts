import { Component, OnInit } from '@angular/core';
import { Voluntaria } from '../../../crud/voluntarias/voluntaria';

@Component({
  selector: 'app-form-voluntarios',
  templateUrl: './form-voluntarios.component.html',
  styleUrls: ['./form-voluntarios.component.css']
})
export class FormVoluntariosComponent implements OnInit {

  voluntaria: Voluntaria = new Voluntaria();
  titulo: string = 'Solicitud voluntariado'; 
 errores: string[];

  constructor() { }

  ngOnInit(): void {
  }

  enviar(){
    //para enviar por mail la info
  }
}
