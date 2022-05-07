import { Component, OnInit } from '@angular/core';
import { EncargadoService } from '../encargado.service';
import { Encargado } from '../encargado';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TokenService } from '../../../security/service/token.service';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-encargado-lista',
  templateUrl: './encargado-lista.component.html',
  styleUrls: ['./encargado-lista.component.css']
})
export class EncargadoListaComponent implements OnInit {

  encargados: Encargado[];
  paginador: any;
  encargadoSeleccionado:Encargado;
  roles: string[];
  isAdmin = false;

  constructor( 
    private encargadoService: EncargadoService,
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
      this.encargadoService.getEncargados(page).subscribe(response => {
        this.encargados = response.content as Encargado[];
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
    this.router.navigate(["/encargados/nuevo"]);
  }

  borrar(encargado: Encargado): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el encargado ${encargado.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.encargadoService.delete(encargado.id).subscribe(
          response => {
            this.encargados = this.encargados.filter(encarg => encarg !== encargado)
            swal.fire(
              'Eliminado!',
              `encargado ${encargado.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }

  abrirModal(encargado:Encargado){
    this.encargadoSeleccionado=encargado;
    this.modalService.abrirModal();
  }


}
