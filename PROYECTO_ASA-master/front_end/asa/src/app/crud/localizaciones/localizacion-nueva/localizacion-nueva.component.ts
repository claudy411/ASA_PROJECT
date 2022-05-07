import { Component, OnInit } from '@angular/core';
import { LocalizacionService } from '../localizacion.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Localizacion } from '../localizacion';

@Component({
  selector: 'app-localizacion-nueva',
  templateUrl: './localizacion-nueva.component.html',
  styleUrls: ['./localizacion-nueva.component.css']
})
export class LocalizacionNuevaComponent implements OnInit {

  public localizacion: Localizacion = new Localizacion();
  public titulo: string = 'Crear localizacion'; 
  public errores: string[];


  constructor(
    private localizacionService: LocalizacionService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  create() {


    this.localizacionService.create(this.localizacion).subscribe(
      data => {
        this.toastr.success('Producto Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/localizaciones']);
      },
      err => {
        this.errores = err.error.errors as string[];
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });

      }
    );

  }



}
