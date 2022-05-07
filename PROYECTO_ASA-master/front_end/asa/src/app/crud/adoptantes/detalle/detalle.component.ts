import { Component, OnInit, Input } from '@angular/core';
import { Adoptante } from '../adoptante';
import { AdoptanteServiceService } from '../adoptante-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ModalService } from '../../../services/modal.service';


@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html'
  
})
export class DetalleComponent implements OnInit {

  @Input() adoptante:Adoptante;
  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private adoptanteService:AdoptanteServiceService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle adoptante: " +this.adoptante.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.adoptanteService.getAdoptante(id).subscribe(adoptante=>{
         this.adoptante=adoptante;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/adoptantes']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
