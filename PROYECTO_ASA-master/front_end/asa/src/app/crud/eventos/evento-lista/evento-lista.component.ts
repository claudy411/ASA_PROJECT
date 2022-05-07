import { Component, OnInit } from '@angular/core';
import { EventoService } from '../evento.service';
import { Evento } from '../evento';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { ModalService } from '../../../services/modal.service';
import { TokenService } from '../../../security/service/token.service';

@Component({
  selector: 'app-evento-lista',
  templateUrl: './evento-lista.component.html',
  styleUrls: ['./evento-lista.component.css']
})
export class EventoListaComponent implements OnInit {

  eventos: Evento[];
  paginador: any;
  eventoSeleccionado:Evento;
  roles: string[];
  isAdmin = false;

  constructor( 
    private eventoService: EventoService,
    private activatedRoute: ActivatedRoute, 
    private router: Router,
    private modalService:ModalService,
    private tokenService: TokenService) { }

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
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }
  rutaCrear(){
    this.router.navigate(["/eventos/nuevo"]);
  }

  borrar(evento: Evento): void {
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
            this.eventos = this.eventos.filter(encarg => encarg !== evento)
            swal.fire(
              'Eliminado!',
              `evento ${evento.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }

  abrirModal(evento:Evento){
    this.eventoSeleccionado=evento;
    this.modalService.abrirModal();
  }


}
