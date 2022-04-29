import { Component, OnInit } from '@angular/core';
import { Voluntaria } from './voluntaria';
import { VoluntariaService } from './voluntaria.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-voluntarias',
  templateUrl: './voluntarias.component.html',
  styleUrls: ['./voluntarias.component.css']
})
export class VoluntariasComponent implements OnInit {

 
  voluntarias: Voluntaria[];
  paginador: any;
 

  constructor(private voluntariaService: VoluntariaService, 
    private activatedRoute: ActivatedRoute) { 
 
    }

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

  delete(voluntaria: Voluntaria): void {
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
              'Eliminado!',
              `Voluntaria ${voluntaria.nombre} eliminada con exito`,
              'success'
            )
          }
        )

      }
    })
  }


} 
