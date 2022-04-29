import { Component, OnInit } from '@angular/core';
import { Evento } from '../evento';
import { EventoService } from '../evento.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-evento',
  templateUrl: './form-evento.component.html',
  styleUrls: ['./form-evento.component.css']
})
export class FormEventoComponent implements OnInit {

  
  public evento: Evento = new Evento();
  public titulo: string = 'Crear evento';
  public errores: string[];

  constructor(private eventoService: EventoService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarEvento();
  }

  cargarEvento(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.eventoService.getEvento(id).subscribe((evento) => this.evento = evento)
      }

    });
  }

  create(): void {

    this.eventoService.create(this.evento).subscribe(
      json => {
        this.router.navigate(['/eventos'])
        Swal.fire('Nueva evento', `${json.mensaje}:  ${json.evento.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.eventoService.update(this.evento)
      .subscribe(json => {
        this.router.navigate(['/eventos'])
        Swal.fire('Evento Actualizado', `${json.mensaje}:  ${json.evento.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
