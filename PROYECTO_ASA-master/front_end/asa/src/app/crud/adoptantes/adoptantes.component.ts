import { Component, OnInit } from '@angular/core';
import { AdoptanteServiceService } from './adoptante-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Adoptante } from './adoptante';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-adoptantes',
  templateUrl: './adoptantes.component.html',
  styleUrls: ['./adoptantes.component.css']
})
export class AdoptantesComponent implements OnInit {

  adoptantes: Adoptante[];
  paginador: any;
 

  constructor(private adoptanteService: AdoptanteServiceService, 
    private activatedRoute: ActivatedRoute, private router: Router) { 
 
    }

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

  }
  rutaCrear(){
    this.router.navigate(["/adoptantes/form"]);
  }

  delete(adoptante: Adoptante): void {
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


} 
