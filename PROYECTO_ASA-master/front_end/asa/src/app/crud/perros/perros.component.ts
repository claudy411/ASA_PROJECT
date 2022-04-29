import { Component, OnInit } from '@angular/core';
import { Perro } from './perro';
import { PerroService } from './perro.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perros',
  templateUrl: './perros.component.html',
  styleUrls: ['./perros.component.css']
})
export class PerrosComponent implements OnInit {

  perros: Perro[];
  paginador: any;
 
 
  constructor(private perroService: PerroService, 
    private activatedRoute: ActivatedRoute) { 
 
    }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.perroService.getPerros(page).subscribe(response => {
        this.perros = response.content as Perro[];
        this.paginador = response;
      });
    })

  }

  delete(perro: Perro): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el perro ${perro.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.perroService.delete(perro.id).subscribe(
          response => {
            this.perros = this.perros.filter(resi => resi !== perro)
            swal.fire(
              'Eliminado!',
              `perro ${perro.nombre} eliminada con exito`,
              'success'
            )
          }
        )

      }
    })
  }


} 
