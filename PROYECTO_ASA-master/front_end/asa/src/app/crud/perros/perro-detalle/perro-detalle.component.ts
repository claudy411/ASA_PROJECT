import { Component, Input, OnInit } from '@angular/core';
import { PerroService } from '../perro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Perro } from '../perro';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-perro-detalle',
  templateUrl: './perro-detalle.component.html',
  styleUrls: ['./perro-detalle.component.css']
})
export class PerroDetalleComponent implements OnInit {

 
  @Input() perro:Perro;
  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private perroService:PerroService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle perro: " +this.perro.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.perroService.getPerro(id).subscribe(perro=>{
         this.perro=perro;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/perros']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
