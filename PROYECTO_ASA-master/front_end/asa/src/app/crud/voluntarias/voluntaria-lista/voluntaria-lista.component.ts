import { Component, OnInit } from '@angular/core';
import { VoluntariaService } from '../voluntaria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Voluntaria } from '../voluntaria';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-voluntaria-lista',
  templateUrl: './voluntaria-lista.component.html',
  styleUrls: ['./voluntaria-lista.component.css']
})
export class VoluntariaListaComponent implements OnInit {

  
  voluntarias: Voluntaria[];
  paginador: any;
  roles: string[];
  isAdmin = false;

  constructor( 
    private voluntariaService: VoluntariaService,
    private activatedRoute: ActivatedRoute, 
    private router: Router,
    private toastr: ToastrService/*,
    private tokenService: TokenService*/) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.voluntariaService.getVoluntarias(page).subscribe(response => {
        this.voluntarias = response.content as Voluntaria[];
        this.paginador = response;
      });
    })

  }
  rutaCrear(){
    this.router.navigate(["/voluntarias/nuevo"]);
  }

  borrar(voluntaria: Voluntaria): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar la voluntaria ${voluntaria.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.voluntariaService.delete(voluntaria.id).subscribe(
          response => {
            this.voluntarias = this.voluntarias.filter(encarg => encarg !== voluntaria)
            swal.fire(
              'Eliminada!',
              `Voluntaria ${voluntaria.nombre} eliminada con exito`,
              'success'
            )
          }
        )

      }
    })
  }
}