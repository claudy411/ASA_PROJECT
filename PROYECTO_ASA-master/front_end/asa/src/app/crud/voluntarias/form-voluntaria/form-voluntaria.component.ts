import { Component, OnInit } from '@angular/core';
import { Voluntaria } from '../voluntaria';
import { VoluntariaService } from '../voluntaria.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-voluntaria',
  templateUrl: './form-voluntaria.component.html',
  styleUrls: ['./form-voluntaria.component.css']
})
export class FormVoluntariaComponent implements OnInit {


  public voluntaria: Voluntaria= new Voluntaria();
  public titulo: string = 'Crear voluntaria';
  public errores: string[];

  constructor(private voluntariaService: VoluntariaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarVoluntaria();
  }

  cargarVoluntaria(): void {

    this.activatedRoute.params.subscribe(params => {

      let id = params['id']
      if (id) {
        this.voluntariaService.getVoluntaria(id).subscribe((voluntaria) => this.voluntaria = voluntaria)
      }

    });
  }

  create(): void {

    this.voluntariaService.create(this.voluntaria).subscribe(
      json => {
        this.router.navigate(['/voluntarias'])
        Swal.fire('Nuevo voluntaria', `${json.mensaje}:  ${json.voluntaria.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })
  }

  update(): void {
    this.voluntariaService.update(this.voluntaria)
      .subscribe(json => {
        this.router.navigate(['/voluntarias'])
        Swal.fire('Voluntaria Actualizada', `${json.mensaje}:  ${json.voluntaria.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}

