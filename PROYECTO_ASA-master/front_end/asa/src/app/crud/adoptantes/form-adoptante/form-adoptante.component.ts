import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Adoptante } from '../adoptante';
import { AdoptanteServiceService } from '../adoptante-service.service';


@Component({
  selector: 'app-form-adoptante',
  templateUrl: './form-adoptante.component.html',
  styleUrls: ['./form-adoptante.component.css']
})
export class FormAdoptanteComponent implements OnInit {

  public adoptante: Adoptante= new Adoptante();
  public titulo: string = 'Crear adoptante';
  public errores: string[];

  constructor(private adoptanteService: AdoptanteServiceService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarAdoptante();
  }

  cargarAdoptante(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.adoptanteService.getAdoptante(id).subscribe((adoptante:any) => this.adoptante = adoptante)
      }

    });
  }

  create(): void {

    this.adoptanteService.create(this.adoptante).subscribe(
      json => {
        this.router.navigate(['/adoptantes'])
        Swal.fire('Nuevo adoptante', `${json.mensaje}:  ${json.adoptante.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.adoptanteService.update(this.adoptante)
      .subscribe(json => {
        this.router.navigate(['/adoptantes'])
        Swal.fire('Adoptante Actualizado', `${json.mensaje}:  ${json.adoptante.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
