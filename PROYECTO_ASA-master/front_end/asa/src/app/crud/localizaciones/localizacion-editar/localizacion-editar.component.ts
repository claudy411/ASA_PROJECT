import { Component, OnInit } from '@angular/core';
import { LocalizacionService } from '../localizacion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Localizacion } from '../localizacion';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-localizacion-editar',
  templateUrl: './localizacion-editar.component.html',
  styleUrls: ['./localizacion-editar.component.css']
})
export class LocalizacionEditarComponent implements OnInit {

  localizacion:Localizacion=null;
  public errores: string[];
  public titulo:string="Editar localizacion";


  constructor(
    private localizacionService: LocalizacionService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.localizacionService.getLocalizacion(id).subscribe(
      data => {
        this.localizacion=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/localizaciones']);
      }
    );
  }
  update(): void {
    this.localizacionService.update(this.localizacion)
      .subscribe(json => {
        this.router.navigate(['/localizaciones'])
        Swal.fire('Localizacion Actualizada', `${json.mensaje}:  ${json.localizacion.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
