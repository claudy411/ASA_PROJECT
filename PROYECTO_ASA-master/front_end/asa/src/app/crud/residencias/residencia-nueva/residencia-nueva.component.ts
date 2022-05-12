import { Component, OnInit } from '@angular/core';
import { Residencia } from '../residencia';
import { ResidenciaService } from '../residencia.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-residencia-nueva',
  templateUrl: './residencia-nueva.component.html',
  styleUrls: ['./residencia-nueva.component.css']
})
export class ResidenciaNuevaComponent implements OnInit {

  public residencia: Residencia = new Residencia();
  public titulo: string = 'Crear residencia'; 
  public errores: string[];


  constructor(
    private residenciaService: ResidenciaService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  // create() {

  //   this.residenciaService.create(this.residencia).subscribe(
  //     json => {
  //       this.router.navigate(['/residencias'])
  //       Swal.fire('Nuevo residencia', `${json.mensaje}:  ${json.residencia.nombre}`, 'success')
  //     },
  //     err => {
  //       this.errores = err.error.errors as string[];
  //       console.error('Codigo del error desde el backend' + err.status);
  //       console.error(err.error.errors);
  //     })

    

  }




