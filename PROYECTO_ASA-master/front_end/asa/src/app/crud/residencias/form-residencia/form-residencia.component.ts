import { Component, OnInit } from '@angular/core';
import { Residencia } from '../residencia';
import { ResidenciaService } from '../residencia.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-residencia',
  templateUrl: './form-residencia.component.html',
  styleUrls: ['./form-residencia.component.css']
})
export class FormResidenciaComponent implements OnInit {

  
  public residencia: Residencia = new Residencia();
  public titulo: string = 'Crear Residencia';
  public errores: string[];

  constructor(private residenciaService: ResidenciaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarResidencia();
  }

  cargarResidencia(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.residenciaService.getResidencia(id).subscribe((residencia) => this.residencia = residencia)
      }

    });
  }

  create(): void {

    this.residenciaService.create(this.residencia).subscribe(
      json => {
        this.router.navigate(['/residencias'])
        Swal.fire('Nueva residencia', `${json.mensaje}:  ${json.residencia.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.residenciaService.update(this.residencia)
      .subscribe(json => {
        this.router.navigate(['/residencias'])
        Swal.fire('Residencia Actualizada', `${json.mensaje}:  ${json.residencia.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
