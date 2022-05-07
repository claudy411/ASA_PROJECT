import { Component, OnInit } from '@angular/core';
import { GatoService } from '../gato.service';
import { Gato } from '../gato';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TokenService } from '../../../security/service/token.service';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-gato-lista',
  templateUrl: './gato-lista.component.html',
  styleUrls: ['./gato-lista.component.css']
})
export class GatoListaComponent implements OnInit {

 
  gatos: Gato[];
  paginador: any;
  gatoSeleccionado:Gato;
  roles: string[];
  isAdmin = false;

  constructor( 
    private gatoService: GatoService,
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
      this.gatoService.getGatos(page).subscribe(response => {
        this.gatos = response.content as Gato[];
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
    this.router.navigate(["/gatos/nuevo"]);
  }

  borrar(gato: Gato): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el gato ${gato.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.gatoService.delete(gato.id).subscribe(
          response => {
            this.gatos = this.gatos.filter(cat => cat !== gato)
            swal.fire(
              'Eliminado!',
              `Gato ${gato.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }

  abrirModal(gato:Gato){
    this.gatoSeleccionado=gato;
    this.modalService.abrirModal();
  }


}