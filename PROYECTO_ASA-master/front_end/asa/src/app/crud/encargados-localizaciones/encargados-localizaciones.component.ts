import { Component, OnInit } from '@angular/core';
import { Encargado } from './encargado';
import { EncargadoService } from './encargado.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-encargados-localizaciones',
  templateUrl: './encargados-localizaciones.component.html',
  styleUrls: ['./encargados-localizaciones.component.css']
})
export class EncargadosLocalizacionesComponent implements OnInit {

  encargados: Encargado[];
  paginador: any;
 

  constructor(private encargadoService: EncargadoService, 
    private activatedRoute: ActivatedRoute) { 
 
    }

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

  }

  delete(encargado: Encargado): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el encargado localizacion ${encargado.nombre} ?`,
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
              `Encargado localizacion ${encargado.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }


} 
