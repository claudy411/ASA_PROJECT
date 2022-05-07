import { Component, OnInit, Input } from '@angular/core';
import { GatoService } from '../gato.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Gato } from '../gato';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-gato-detalle',
  templateUrl: './gato-detalle.component.html'
})
export class GatoDetalleComponent implements OnInit {

  @Input() gato:Gato=null;
  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private gatoService:GatoService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle gato: " +this.gato.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.gatoService.getGato(id).subscribe(gato=>{
         this.gato=gato;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/gatos']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
