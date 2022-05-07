import { Component, OnInit } from '@angular/core';
import { AdoptanteServiceService } from '../adoptante-service.service';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Adoptante } from '../adoptante';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-nuevo',
  templateUrl: './nuevo.component.html',
  styleUrls: ['./nuevo.component.css']
})
export class NuevoComponent implements OnInit {

  public adoptante: Adoptante = new Adoptante();
  public titulo: string = 'Crear adoptante'; 
  public errores: string[];


  constructor(
    private adoptanteService: AdoptanteServiceService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  create() {
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

}
