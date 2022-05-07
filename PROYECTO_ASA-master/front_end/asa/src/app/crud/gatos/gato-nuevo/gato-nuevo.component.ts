import { Component, OnInit } from '@angular/core';
import { Gato } from '../gato';
import { GatoService } from '../gato.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-gato-nuevo',
  templateUrl: './gato-nuevo.component.html',
  styleUrls: ['./gato-nuevo.component.css']
})
export class GatoNuevoComponent implements OnInit {

 
  public gato: Gato = new Gato();
  public titulo: string = 'Crear gato'; 
  public errores: string[];


  constructor(
    private gatoService: GatoService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  create() {

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


}
