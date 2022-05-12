import { Component, OnInit } from '@angular/core';
import { Voluntaria } from '../voluntaria';
import { VoluntariaService } from '../voluntaria.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-voluntaria-nuevo',
  templateUrl: './voluntaria-nuevo.component.html',
  styleUrls: ['./voluntaria-nuevo.component.css']
})
export class VoluntariaNuevoComponent implements OnInit {

  public voluntaria: Voluntaria = new Voluntaria();
  public titulo: string = 'Crear voluntari@'; 
  public errores: string[];


  constructor(
    private voluntariaService: VoluntariaService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  create() {

    this.voluntariaService.create(this.voluntaria).subscribe(
      json => {
        this.router.navigate(['/residencias'])
        Swal.fire('Nuev@ voluntari@', `${json.mensaje}:  ${json.voluntaria.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })


  }



}
