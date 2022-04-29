import { Component, OnInit } from '@angular/core';
import { GatoService } from './gato.service';
import { ActivatedRoute } from '@angular/router';
import { Gato } from './gato';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-gatos',
  templateUrl: './gatos.component.html',
  styleUrls: ['./gatos.component.css']
})
export class GatosComponent implements OnInit {

  gatos: Gato[];
  paginador: any;
 
 
  constructor(private gatoService: GatoService, 
    private activatedRoute: ActivatedRoute) { 
 
    }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.gatoService.getGatos(page).subscribe(response => {
        this.gatos = response.content as Gato[];
        this.paginador = response;
      });
    })

  }

  delete(gato: Gato): void {
    const swal = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success ms-2',
        cancelButton: 'btn btn-danger me-2'
      },
      buttonsStyling: false
    })
    swal.fire({
      title: 'Estás seguro?',
      text: `¿Seguro que desea eliminar el gato ${gato.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.gatoService.delete(gato.id).subscribe(
          response => {
            this.gatos = this.gatos.filter(cat => cat !== gato)
            swal.fire(
              'Eliminado!',
              `Gato ${gato.nombre} eliminado con exito`,
              'success'
            )
          }
        )

      }
    })
  }


} 

