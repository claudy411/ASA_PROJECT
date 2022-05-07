import { Component, OnInit } from '@angular/core';
import { Perro } from '../perro';
import { PerroService } from '../perro.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Residencia } from '../../residencias/residencia';

@Component({
  selector: 'app-perro-editar',
  templateUrl: './perro-editar.component.html',
  styleUrls: ['./perro-editar.component.css']
})
export class PerroEditarComponent implements OnInit {

 
  public perro: Perro = new Perro();
  public residencias:Residencia[];
  public titulo: string = 'Editar perro';
  public errores: string[];

  constructor(
    private perroService: PerroService, 
    private router: Router, 
    private activatedRoute: ActivatedRoute) { }

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

  update(): void {
    this.perroService.update(this.perro)
      .subscribe(json => {
        this.router.navigate(['/perros'])
        Swal.fire('Perro Actualizado', `${json.mensaje}:  ${json.perro.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

 
  
   compararResidencia(o1: Residencia, o2: Residencia): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }

    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id;
  }
  

}