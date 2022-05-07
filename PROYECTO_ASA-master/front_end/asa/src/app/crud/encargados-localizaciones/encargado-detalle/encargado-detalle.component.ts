import { Component, Input, OnInit } from '@angular/core';
import { EncargadoService } from '../encargado.service';
import { Encargado } from '../encargado';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-encargado-detalle',
  templateUrl: './encargado-detalle.component.html'
})
export class EncargadoDetalleComponent implements OnInit {

  @Input()encargado:Encargado;
  titulo:string="";

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private encargadoService:EncargadoService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle encargado: " +this.encargado.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.encargadoService.getencargado(id).subscribe(encargado=>{
         this.encargado=encargado;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/encargados']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
