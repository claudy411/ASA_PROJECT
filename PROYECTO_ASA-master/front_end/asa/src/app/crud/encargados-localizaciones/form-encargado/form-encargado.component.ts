import { Component, OnInit } from '@angular/core';
import { Encargado } from '../encargado';
import { EncargadoService } from '../encargado.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-encargado',
  templateUrl: './form-encargado.component.html',
  styleUrls: ['./form-encargado.component.css']
})
export class FormEncargadoComponent implements OnInit {

  public encargado: Encargado= new Encargado();
  public titulo: string = 'Crear encargado';
  public errores: string[];

  constructor(private encargadoService: EncargadoService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarencargado();
  }

  cargarencargado(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.encargadoService.getEncargado(id).subscribe((encargado) => this.encargado = encargado)
      }

    });
  }

  create(): void {

    this.encargadoService.create(this.encargado).subscribe(
      json => {
        this.router.navigate(['/encargados'])
        Swal.fire('Nuevo encargado', `${json.mensaje}:  ${json.encargado.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.encargadoService.update(this.encargado)
      .subscribe(json => {
        this.router.navigate(['/encargados'])
        Swal.fire('Encargado Actualizado', `${json.mensaje}:  ${json.encargado.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
