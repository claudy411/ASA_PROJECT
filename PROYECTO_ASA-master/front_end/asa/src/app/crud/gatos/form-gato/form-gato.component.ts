import { Component, OnInit } from '@angular/core';
import { Gato } from '../gato';
import { GatoService } from '../gato.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-gato',
  templateUrl: './form-gato.component.html',
  styleUrls: ['./form-gato.component.css']
})
export class FormGatoComponent implements OnInit {

  
  public gato: Gato = new Gato();
  public titulo: string = 'Crear gato';
  public errores: string[];

  constructor(private gatoService: GatoService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarGato();
  }

  cargarGato(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.gatoService.getGato(id).subscribe((gato) => this.gato = gato)
      }

    });
  }

  create(): void {

    this.gatoService.create(this.gato).subscribe(
      json => {
        this.router.navigate(['/gatos'])
        Swal.fire('Nuevo gato', `${json.mensaje}:  ${json.gato.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.gatoService.update(this.gato)
      .subscribe(json => {
        this.router.navigate(['/gatos'])
        Swal.fire('gato Actualizado', `${json.mensaje}:  ${json.gato.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
