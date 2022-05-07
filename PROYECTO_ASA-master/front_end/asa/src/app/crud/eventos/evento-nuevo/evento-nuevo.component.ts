import { Component, OnInit } from '@angular/core';
import { Evento } from '../evento';
import { EventoService } from '../evento.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-evento-nuevo',
  templateUrl: './evento-nuevo.component.html',
  styleUrls: ['./evento-nuevo.component.css']
})
export class EventoNuevoComponent implements OnInit {

 
  public evento: Evento = new Evento();
  public titulo: string = 'Crear evento'; 
  public errores: string[];


  constructor(
    private eventoService: EventoService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  create() {
    this.eventoService.create(this.evento).subscribe(
      json => {
        this.router.navigate(['/eventos'])
        Swal.fire('Nuevo evento', `${json.mensaje}:  ${json.evento.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo del error desde el backend' + err.status);
        console.error(err.error.errors);
      })

    // this.eventoService.create(this.evento).subscribe(
    //   data => {
    //     this.toastr.success('Producto Creado', 'OK', {
    //       timeOut: 3000, positionClass: 'toast-top-center'
    //     });
    //     this.router.navigate(['/eventos']);
    //   },
    //   err => {
    //     this.errores = err.error.errors as string[];
    //     this.toastr.error(err.error.mensaje, 'Fail', {
    //       timeOut: 3000, positionClass: 'toast-top-center',
    //     });

    //   }
    // );

  }


}

