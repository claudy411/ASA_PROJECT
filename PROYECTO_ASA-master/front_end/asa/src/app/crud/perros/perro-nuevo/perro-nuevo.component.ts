import { Component, OnInit } from '@angular/core';
import { Perro } from '../perro';
import { PerroService } from '../perro.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Residencia } from '../../residencias/residencia';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perro-nuevo',
  templateUrl: './perro-nuevo.component.html',
  styleUrls: ['./perro-nuevo.component.css']
})
export class PerroNuevoComponent implements OnInit {

  public perro: Perro = new Perro();
  public residencias:Residencia[];
  public titulo: string = 'Crear perro';
  public errores: string[];

  constructor(private perroService: PerroService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarPerro();
  }

  cargarPerro(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.perroService.getPerro(id).subscribe((perro) => this.perro = perro)
      }

    });
 
    this.perroService.getResidencias().subscribe(residencias =>this.residencias=residencias);
  }


  create(): void {

    this.perroService.create(this.perro).subscribe(
      json => {
        this.router.navigate(['/perros'])
        Swal.fire('Nuevo perro', `${json.mensaje}:  ${json.perro.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  compararResidencia(o1: Residencia, o2: Residencia): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }

    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id;
  }

}
