import { Component, OnInit } from '@angular/core';
import { ResidenciaService } from '../residencia.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Residencia } from '../residencia';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-residencia-editar',
  templateUrl: './residencia-editar.component.html',
  styleUrls: ['./residencia-editar.component.css']
})
export class ResidenciaEditarComponent implements OnInit {

  public residencia:Residencia=null;
  public errores: string[];
  public titulo:string="Editar residencia";


  constructor(
    private residenciaService: ResidenciaService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.residenciaService.getResidencia(id).subscribe(
      data => {
        this.residencia=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/residencias']);
      }
    );
  }
  update(): void {
    this.residenciaService.update(this.residencia)
      .subscribe(json => {
        this.router.navigate(['/residencias'])
        Swal.fire('residencia Actualizado', `${json.mensaje}:  ${json.residencia.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
