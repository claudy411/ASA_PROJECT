import { Component, OnInit } from '@angular/core';
import { Adoptante } from '../adoptante';
import { AdoptanteServiceService } from '../adoptante-service.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TokenService } from '../../../security/service/token.service';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  adoptantes: Adoptante[];
  paginador: any;
  adoptanteSeleccionado:Adoptante; 
  roles: string[];
  isAdmin = false;
 
  constructor( 
    private adoptanteService: AdoptanteServiceService,
    private activatedRoute: ActivatedRoute, 
    private router: Router,
    private toastr: ToastrService,
    private tokenService: TokenService,
    private modalService:ModalService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.adoptanteService.getAdoptantes(page).subscribe(response => {
        this.adoptantes = response.content as Adoptante[];
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
    this.router.navigate(["/adoptantes/nuevo"]);
  }

  borrar(adoptante: Adoptante): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el adoptante ${adoptante.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.adoptanteService.delete(adoptante.id).subscribe(
          response => {
            this.adoptantes = this.adoptantes.filter(encarg => encarg !== adoptante)
            swal.fire(
              'Eliminado!',
              `Adoptante ${adoptante.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }

  abrirModal(adoptante:Adoptante){
    this.adoptanteSeleccionado=adoptante;
    this.modalService.abrirModal();
  }

} 



  /*
productos: Producto[] = [];
  roles: string[];
  isAdmin = false;

  constructor(
    private productoService: ProductoService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarProductos();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  cargarProductos(): void {
    this.productoService.lista().subscribe(
      data => {
        this.productos = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: number) {
    this.productoService.delete(id).subscribe(
      data => {
        this.toastr.success('Producto Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarProductos();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

  */

