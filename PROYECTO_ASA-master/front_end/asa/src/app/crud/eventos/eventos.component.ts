import { Component, OnInit } from '@angular/core';
import { Evento } from './evento';
import { EventoService } from './evento.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-eventos',
  templateUrl: './eventos.component.html',
  styleUrls: ['./eventos.component.css']
})
export class EventosComponent implements OnInit {

  eventos: Evento[];
  paginador: any;
 

  constructor(private eventoService: EventoService, 
    private activatedRoute: ActivatedRoute) { 

    }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.eventoService.getEventos(page).subscribe(response => {
        this.eventos = response.content as Evento[];
        this.paginador = response;
      });
    })

  }

  delete(evento: Evento): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el evento ${evento.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.eventoService.delete(evento.id).subscribe(
          response => {
            this.eventos = this.eventos.filter(resi => resi !== evento)
            swal.fire(
              'Eliminado!',
              `Evento ${evento.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }


}
