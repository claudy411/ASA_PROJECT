import { Component, OnInit } from '@angular/core';
import { Localizacion } from '../localizacion';
import { LocalizacionService } from '../localizacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-localizacion',
  templateUrl: './form-localizacion.component.html',
  styleUrls: ['./form-localizacion.component.css']
})
export class FormLocalizacionComponent implements OnInit {

  public localizacion: Localizacion = new Localizacion();
  public titulo: string = 'Crear localizacion';
  public errores: string[];

  constructor(private localizacionService: LocalizacionService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarLocalizacion();
  }

  cargarLocalizacion(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.localizacionService.getLocalizacion(id).subscribe((localizacion) => this.localizacion = localizacion)
      }

    });
  }

  create(): void {

    this.localizacionService.create(this.localizacion).subscribe(
      json => {
        this.router.navigate(['/localizaciones'])
        Swal.fire('Nueva localizacion', `${json.mensaje}:  ${json.localizacion.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.localizacionService.update(this.localizacion)
      .subscribe(json => {
        this.router.navigate(['/localizaciones'])
        Swal.fire('Localizacion Actualizada', `${json.mensaje}:  ${json.localizacion.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
