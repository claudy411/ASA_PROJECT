import { Component, OnInit } from '@angular/core';
import { VoluntariaService } from '../voluntaria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Voluntaria } from '../voluntaria';

@Component({
  selector: 'app-voluntaria-detalle',
  templateUrl: './voluntaria-detalle.component.html',
  styleUrls: ['./voluntaria-detalle.component.css']
})
export class VoluntariaDetalleComponent implements OnInit {

  
  voluntaria:Voluntaria=null;

  constructor(
    private voluntariaService: VoluntariaService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.voluntariaService.getVoluntaria(id).subscribe(
      data => {
        this.voluntaria=data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.volver();
      }
    );
  }

  volver(){
    this.router.navigate(['/voluntarias']);
  }
}
