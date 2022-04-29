import { Component, OnInit } from '@angular/core';
import { Localizacion } from './localizacion';
import { LocalizacionService } from './localizacion.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-localizaciones',
  templateUrl: './localizaciones.component.html',
  styleUrls: ['./localizaciones.component.css']
})
export class LocalizacionesComponent implements OnInit {

  localizaciones: Localizacion[];
  paginador: any;
 

  constructor(private localizacionService: LocalizacionService, 
    private activatedRoute: ActivatedRoute) { 
 
    }

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

  }

  delete(localizacion: Localizacion): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar la localizacion ${localizacion.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.localizacionService.delete(localizacion.id).subscribe(
          response => {
            this.localizaciones = this.localizaciones.filter(loc => loc !== localizacion)
            swal.fire(
              'Eliminado!',
              `Localizacion ${localizacion.nombre} eliminada con exito`,
              'success'
            )
          }
        )

      }
    })
  }


} 
