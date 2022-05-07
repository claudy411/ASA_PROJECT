import { Component, OnInit } from '@angular/core';
import { EventoService } from '../evento.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Evento } from '../evento';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-evento-editar',
  templateUrl: './evento-editar.component.html',
  styleUrls: ['./evento-editar.component.css']
})
export class EventoEditarComponent implements OnInit {

  public evento:Evento=null;
  public errores: string[];
  public titulo:string="Editar evento";


  constructor(
    private eventoService: EventoService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.eventoService.getEvento(id).subscribe(
      data => {
        this.evento=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/eventos']);
      }
    );
  }
  update(): void {
    this.eventoService.update(this.evento)
      .subscribe(json => {
        this.router.navigate(['/eventos'])
        Swal.fire('evento Actualizado', `${json.mensaje}:  ${json.evento.nombre}`, 'success')
      },
      err => {
        this.errores= err.error.errors as string[];
        console.error('Codigo del error desde el backend'+ err.status);
        console.error(err.error.errors);
      }
      )
  }

}
