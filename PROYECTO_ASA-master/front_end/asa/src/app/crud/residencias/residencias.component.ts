import { Component, OnInit } from '@angular/core';
import { Residencia } from './residencia';
import { ResidenciaService } from './residencia.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-residencias',
  templateUrl: './residencias.component.html',
  styleUrls: ['./residencias.component.css']
})
export class ResidenciasComponent implements OnInit {

  
  residencias: Residencia[];
  paginador: any;
 

  constructor(private residenciaService: ResidenciaService, 
    private activatedRoute: ActivatedRoute) { 

    }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.residenciaService.getResidencias(page).subscribe(response => {
        this.residencias = response.content as Residencia[];
        this.paginador = response;
      });
    })

  }

  delete(residencia: Residencia): void {
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
        this.residenciaService.delete(residencia.id).subscribe(
          response => {
            this.residencias = this.residencias.filter(resi => resi !== residencia)
            swal.fire(
              'Eliminado!',
              `Residencia ${residencia.nombre} eliminada con exito`,
              'success'
            )
          }
        )

      }
    })
  }


}
