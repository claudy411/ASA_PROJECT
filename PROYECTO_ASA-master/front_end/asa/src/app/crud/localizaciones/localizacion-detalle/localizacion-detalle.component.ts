import { Component, Input, OnInit } from '@angular/core';
import { LocalizacionService } from '../localizacion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Localizacion } from '../localizacion';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-localizacion-detalle',
  templateUrl: './localizacion-detalle.component.html'
})
export class LocalizacionDetalleComponent implements OnInit {

  @Input() localizacion:Localizacion;
  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private localizacionService:LocalizacionService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle localizacion: " +this.localizacion.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.localizacionService.getLocalizacion(id).subscribe(localizacion=>{
         this.localizacion=localizacion;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/localizaciones']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
