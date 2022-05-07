import { Component, OnInit } from '@angular/core';
import { GatoService } from '../gato.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Gato } from '../gato';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-gato-editar',
  templateUrl: './gato-editar.component.html',
  styleUrls: ['./gato-editar.component.css']
})
export class GatoEditarComponent implements OnInit {

  public gato:Gato;
  public errores: string[];
  public titulo:string="Editar gato";


  constructor(
    private gatoService: GatoService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.gatoService.getGato(id).subscribe(
      data => {
        this.gato=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/gatos']);
      }
    );
  }
  update(): void {
    this.gatoService.update(this.gato)
      .subscribe(json => {
        this.router.navigate(['/gatos'])
        Swal.fire('Gato Actualizado', `${json.mensaje}:  ${json.gato.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}


