import { Component, OnInit, Input } from '@angular/core';
import { VoluntariaService } from '../voluntaria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Voluntaria } from '../voluntaria';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-voluntaria-detalle',
  templateUrl: './voluntaria-detalle.component.html',
  styleUrls: ['./voluntaria-detalle.component.css']
})
export class VoluntariaDetalleComponent implements OnInit {

  
  @Input() voluntaria:Voluntaria;
  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private voluntariaService:VoluntariaService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle voluntaria: " +this.voluntaria.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.voluntariaService.getVoluntaria(id).subscribe(voluntaria=>{
         this.voluntaria=voluntaria;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/voluntarias']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}


 