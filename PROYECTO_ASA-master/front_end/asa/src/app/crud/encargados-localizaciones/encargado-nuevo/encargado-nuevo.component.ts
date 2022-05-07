import { Component, OnInit } from '@angular/core';
import { Encargado } from '../encargado';
import { EncargadoService } from '../encargado.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-encargado-nuevo',
  templateUrl: './encargado-nuevo.component.html',
  styleUrls: ['./encargado-nuevo.component.css']
})
export class EncargadoNuevoComponent implements OnInit {

 
  public encargado: Encargado = new Encargado();
  public titulo: string = 'Crear encargado'; 
  public errores: string[];


  constructor(
    private encargadoService: EncargadoService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  create() {
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
}
