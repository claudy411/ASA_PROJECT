import { Component, OnInit } from '@angular/core';
import { EncargadoService } from '../encargado.service';
import { Encargado } from '../encargado';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-encargado-editar',
  templateUrl: './encargado-editar.component.html',
  styleUrls: ['./encargado-editar.component.css']
})
export class EncargadoEditarComponent implements OnInit {

  public encargado:Encargado=null;
  public errores: string[];
  public titulo:string="Editar encargado";


  constructor(
    private encargadoService: EncargadoService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.encargadoService.getencargado(id).subscribe(
      data => {
        this.encargado=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/encargados']);
      }
    );
  }
  update(): void {
    this.encargadoService.update(this.encargado)
      .subscribe(json => {
        this.router.navigate(['/encargados'])
        Swal.fire('encargado Actualizado', `${json.mensaje}:  ${json.encargado.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }
}