import { Component, OnInit } from '@angular/core';
import { VoluntariaService } from '../voluntaria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Voluntaria } from '../voluntaria';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-voluntaria-editar',
  templateUrl: './voluntaria-editar.component.html',
  styleUrls: ['./voluntaria-editar.component.css']
})
export class VoluntariaEditarComponent implements OnInit {

  public voluntaria:Voluntaria=null;
  public errores: string[];
  public titulo:string="Editar voluntaria";


  constructor(
    private voluntariaService: VoluntariaService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.voluntariaService.getVoluntaria(id).subscribe(
      data => {
        this.voluntaria=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/voluntarias']);
      }
    );
  }
  update(): void {
    this.voluntariaService.update(this.voluntaria)
      .subscribe(json => {
        this.router.navigate(['/voluntarias'])
        Swal.fire('voluntaria Actualizado', `${json.mensaje}:  ${json.voluntaria.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
