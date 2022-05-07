import { Component, OnInit, Input } from '@angular/core';
import { ResidenciaService } from '../residencia.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Residencia } from '../residencia';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-residencia-detalle',
  templateUrl: './residencia-detalle.component.html',
  styleUrls: ['./residencia-detalle.component.css']
})
export class ResidenciaDetalleComponent implements OnInit {

  @Input() residencia:Residencia;
  titulo:string=""; 

  constructor(
 
     private activatedRoute: ActivatedRoute,
     private router: Router,
    public modalService:ModalService,
    private residenciaService:ResidenciaService
  ) { }

  ngOnInit(): void {
    
    this.titulo="Detalle residencia: " +this.residencia.nombre;
   this.activatedRoute.paramMap.subscribe(params=>{
     let id:number=+params.get('id');
     if(id){
       this.residenciaService.getResidencia(id).subscribe(residencia=>{
         this.residencia=residencia;
       })
     }
   })
  }

  volver(){
    this.router.navigate(['/residencias']);
  }
  cerrarModal() {
    this.modalService.cerrarModal();
    
  }
}
