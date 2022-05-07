import { Component, OnInit } from '@angular/core';
import { Voluntaria } from '../voluntaria';
import { VoluntariaService } from '../voluntaria.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-voluntaria-nuevo',
  templateUrl: './voluntaria-nuevo.component.html',
  styleUrls: ['./voluntaria-nuevo.component.css']
})
export class VoluntariaNuevoComponent implements OnInit {

  public voluntaria: Voluntaria = new Voluntaria();
  public titulo: string = 'Crear voluntaria'; 
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
      data => {
        this.toastr.success('Voluntaria Creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/voluntarias']);
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
