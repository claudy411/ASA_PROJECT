import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListaComponent } from './crud/adoptantes/lista/lista.component';
import { NuevoComponent } from './crud/adoptantes/nuevo/nuevo.component';
import { EditarComponent } from './crud/adoptantes/editar/editar.component';
import { EncargadoListaComponent } from './crud/encargados-localizaciones/encargado-lista/encargado-lista.component';
import { EncargadoDetalleComponent } from './crud/encargados-localizaciones/encargado-detalle/encargado-detalle.component';
import { EncargadoNuevoComponent } from './crud/encargados-localizaciones/encargado-nuevo/encargado-nuevo.component';
import { EncargadoEditarComponent } from './crud/encargados-localizaciones/encargado-editar/encargado-editar.component';
import { EventoListaComponent } from './crud/eventos/evento-lista/evento-lista.component';
import { EventoNuevoComponent } from './crud/eventos/evento-nuevo/evento-nuevo.component';
import { EventoEditarComponent } from './crud/eventos/evento-editar/evento-editar.component';
import { EventoDetalleComponent } from './crud/eventos/evento-detalle/evento-detalle.component';
import { GatoListaComponent } from './crud/gatos/gato-lista/gato-lista.component';
import { GatoNuevoComponent } from './crud/gatos/gato-nuevo/gato-nuevo.component';
import { GatoDetalleComponent } from './crud/gatos/gato-detalle/gato-detalle.component';
import { GatoEditarComponent } from './crud/gatos/gato-editar/gato-editar.component';
import { LocalizacionListaComponent } from './crud/localizaciones/localizacion-lista/localizacion-lista.component';
import { LocalizacionNuevaComponent } from './crud/localizaciones/localizacion-nueva/localizacion-nueva.component';
import { LocalizacionDetalleComponent } from './crud/localizaciones/localizacion-detalle/localizacion-detalle.component';
import { LocalizacionEditarComponent } from './crud/localizaciones/localizacion-editar/localizacion-editar.component';
import { ResidenciaListaComponent } from './crud/residencias/residencia-lista/residencia-lista.component';
import { ResidenciaNuevaComponent } from './crud/residencias/residencia-nueva/residencia-nueva.component';
import { ResidenciaDetalleComponent } from './crud/residencias/residencia-detalle/residencia-detalle.component';
import { ResidenciaEditarComponent } from './crud/residencias/residencia-editar/residencia-editar.component';
import { VoluntariaListaComponent } from './crud/voluntarias/voluntaria-lista/voluntaria-lista.component';
import { VoluntariaDetalleComponent } from './crud/voluntarias/voluntaria-detalle/voluntaria-detalle.component';
import { VoluntariaNuevoComponent } from './crud/voluntarias/voluntaria-nuevo/voluntaria-nuevo.component';
import { VoluntariaEditarComponent } from './crud/voluntarias/voluntaria-editar/voluntaria-editar.component';
import { PerroListaComponent } from './crud/perros/perro-lista/perro-lista.component';
import { PerroNuevoComponent } from './crud/perros/perro-nuevo/perro-nuevo.component';
import { PerroDetalleComponent } from './crud/perros/perro-detalle/perro-detalle.component';
import { PerroEditarComponent } from './crud/perros/perro-editar/perro-editar.component';
import { LoginComponent } from './security/auth/login/login.component';
import { RegistroComponent } from './security/auth/registro/registro.component';
import { GuardService as guard } from './security/guards/guard.service';
import { InicioComponent } from './inicio/inicio.component';
import { DashboardComponent } from './registrados/dashboard.component';
import { EventosComponent } from './_paginas/eventos/eventos.component';
import { NosotrosComponent } from './_paginas/nosotros/nosotros.component';
import { AdoptaComponent } from './_paginas/adopta/adopta.component';
import { AcogeComponent } from './_paginas/acoge/acoge.component';
import { ContactoComponent } from './_paginas/contacto/contacto.component';
import { VoluntariadoComponent } from './_paginas/voluntariado/voluntariado.component';

const routes: Routes = [

  { path: '', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'dashboard', component: DashboardComponent },

  { path: 'pagina/eventos', component: EventosComponent },
  { path: 'pagina/nosotros', component: NosotrosComponent },
  { path: 'pagina/adopta', component: AdoptaComponent },
  { path: 'pagina/acoge', component: AcogeComponent },
  { path: 'pagina/contacto', component: ContactoComponent },
  { path: 'pagina/voluntariado', component: VoluntariadoComponent },

  { path: "adoptantes", component: ListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "adoptantes/page/:page", component: ListaComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "adoptantes/nuevo", component: NuevoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  // { path: "adoptantes/detalle/:id", component: DetalleComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "adoptantes/editar/:id", component: EditarComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },

  { path: "encargados", component: EncargadoListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "encargados/page/:page", component: EncargadoListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "encargados/nuevo", component: EncargadoNuevoComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "encargados/detalle/:id", component: EncargadoDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "encargados/editar/:id", component: EncargadoEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },

  { path: "eventos", component: EventoListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "eventos/page/:page", component: EventoListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "eventos/nuevo", component: EventoNuevoComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "eventos/detalle/:id", component: EventoDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "eventos/editar/:id", component: EventoEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },

  { path: "gatos", component: GatoListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "gatos/page/:page", component: GatoListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "gatos/nuevo", component: GatoNuevoComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "gatos/detalle/:id", component: GatoDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "gatos/editar/:id", component: GatoEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },

  { path: "localizaciones", component: LocalizacionListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "localizaciones/page/:page", component: LocalizacionListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "localizaciones/nuevo", component: LocalizacionNuevaComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "localizaciones/detalle/:id", component: LocalizacionDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "localizaciones/editar/:id", component: LocalizacionEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },

  { path: "perros", component: PerroListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "perros/page/:page", component: PerroListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "perros/nuevo", component: PerroNuevoComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "perros/detalle/:id", component: PerroDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "perros/editar/:id", component: PerroEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },

  { path: "residencias", component: ResidenciaListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "residencias/page/:page", component: ResidenciaListaComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "residencias/nuevo", component: ResidenciaNuevaComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "residencias/detalle/:id", component: ResidenciaDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "residencias/editar/:id", component: ResidenciaEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  
  { path: "voluntarias", component: VoluntariaListaComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "voluntarias/page/:page", component: VoluntariaListaComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "voluntarias/nuevo", component: VoluntariaNuevoComponent, canActivate:  [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: "voluntarias/detalle/:id", component: VoluntariaDetalleComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },
  { path: "voluntarias/editar/:id", component: VoluntariaEditarComponent, canActivate:  [guard], data: { expectedRol: ['admin'] } },



  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
