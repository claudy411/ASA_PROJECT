import { Component, OnInit } from '@angular/core';
import { LocalizacionService } from '../localizacion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Localizacion } from '../localizacion';
import Swal from 'sweetalert2';
import { TokenService } from '../../../security/service/token.service';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-localizacion-lista',
  templateUrl: './localizacion-lista.component.html',
  styleUrls: ['./localizacion-lista.component.css']
})
export class LocalizacionListaComponent implements OnInit {

  localizaciones: Localizacion[];
  paginador: any;
  localizacionSeleccionada:Localizacion;
  roles: string[];
  isAdmin = false;

  constructor( 
    private localizacionService: LocalizacionService,
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
      this.localizacionService.getLocalizaciones(page).subscribe(response => {
        this.localizaciones = response.content as Localizacion[];
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
    this.router.navigate(["/localizaciones/nuevo"]);
  }

  borrar(localizacion: Localizacion): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el localizacion ${localizacion.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.localizacionService.delete(localizacion.id).subscribe(
          response => {
            this.localizaciones = this.localizaciones.filter(encarg => encarg !== localizacion)
            swal.fire(
              'Eliminado!',
              `localizacion ${localizacion.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }

  abrirModal(localizacion:Localizacion){
    this.localizacionSeleccionada=localizacion;
    this.modalService.abrirModal();
  }
}
