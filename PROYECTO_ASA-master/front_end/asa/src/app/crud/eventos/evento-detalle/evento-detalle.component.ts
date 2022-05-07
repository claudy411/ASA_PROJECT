import { Component, Input, OnInit } from '@angular/core';
import { EventoService } from '../evento.service';
import { Evento } from '../evento';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-evento-detalle',
  templateUrl: './evento-detalle.component.html',
  styleUrls: ['./evento-detalle.component.css']
})
export class EventoDetalleComponent implements OnInit {

  @Input() evento:Evento;

  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private eventoService:EventoService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle evento: " +this.evento.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.eventoService.getEvento(id).subscribe(evento=>{
         this.evento=evento;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/eventos']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
