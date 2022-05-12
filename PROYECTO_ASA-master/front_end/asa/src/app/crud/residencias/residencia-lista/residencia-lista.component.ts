import { Component, OnInit } from '@angular/core';
import { ResidenciaService } from '../residencia.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Residencia } from '../residencia';
import Swal from 'sweetalert2';
import { TokenService } from '../../../security/service/token.service';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-residencia-lista',
  templateUrl: './residencia-lista.component.html',
  styleUrls: ['./residencia-lista.component.css']
})
export class ResidenciaListaComponent implements OnInit {

  residencias: Residencia[];
  paginador: any;
  residenciaSeleccionada:Residencia;
  roles: string[];
  isAdmin = false;

  constructor( 
    private residenciaService: ResidenciaService,
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
      this.residenciaService.listar(page).subscribe(response => {
        this.residencias = response.content as Residencia[];
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
    this.router.navigate(["/residencias/nuevo"]);
  }

  borrar(residencia: Residencia): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar la residencia ${residencia.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.residenciaService.eliminar(residencia.id).subscribe(
          response => {
            this.residencias = this.residencias.filter(resi => resi !== residencia)
            swal.fire(
              'Eliminada!',
              `residencia ${residencia.nombre} eliminada con exito`,
              'success'
            )
          }
        )

      }
    })
  }

  abrirModal(residencia:Residencia){
    this.residenciaSeleccionada=residencia;
    this.modalService.abrirModal();
  }

}