import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdoptantesComponent } from './crud/adoptantes/adoptantes.component';
import { FormAdoptanteComponent } from './crud/adoptantes/form-adoptante/form-adoptante.component';
import { EncargadosLocalizacionesComponent } from './crud/encargados-localizaciones/encargados-localizaciones.component';
import { EventosComponent } from './crud/eventos/eventos.component';
import { GatosComponent } from './crud/gatos/gatos.component';
import { LocalizacionesComponent } from './crud/localizaciones/localizaciones.component';
import { PerrosComponent } from './crud/perros/perros.component';
import { ResidenciasComponent } from './crud/residencias/residencias.component';
import { VoluntariasComponent } from './crud/voluntarias/voluntarias.component';
import { FormEncargadoComponent } from './crud/encargados-localizaciones/form-encargado/form-encargado.component';
import { FormEventoComponent } from './crud/eventos/form-evento/form-evento.component';
import { FormLocalizacionComponent } from './crud/localizaciones/form-localizacion/form-localizacion.component';
import { FormPerroComponent } from './crud/perros/form-perro/form-perro.component';
import { FormGatoComponent } from './crud/gatos/form-gato/form-gato.component';
import { FormResidenciaComponent } from './crud/residencias/form-residencia/form-residencia.component';
import { FormVoluntariaComponent } from './crud/voluntarias/form-voluntaria/form-voluntaria.component';

const routes: Routes = [
  { path: "adoptantes", component: AdoptantesComponent},
  { path: "adoptantes/form", component: FormAdoptanteComponent},
  { path: "adoptantes/form/:id", component: FormAdoptanteComponent},
  { path: "adoptantes/page/:page", component: AdoptantesComponent},

  { path: "encargados", component: EncargadosLocalizacionesComponent},
  { path: "encargados/page/:page", component: EncargadosLocalizacionesComponent},
  { path: "encargados/form", component: FormEncargadoComponent},
  { path: "encargados/form/:id", component: FormEncargadoComponent},
  
  { path: "eventos", component: EventosComponent},
  { path: "eventos/page/:page", component: EventosComponent},
  { path: "eventos/form", component: FormEventoComponent},
  { path: "eventos/form/:id", component: FormEventoComponent},

  { path: "localizaciones", component: LocalizacionesComponent},
  { path: "localizaciones/page/:page", component: LocalizacionesComponent},
  { path: "localizaciones/form", component: FormLocalizacionComponent},
  { path: "localizaciones/form/:id", component: FormLocalizacionComponent},

  { path: "perros", component: PerrosComponent},
  { path: "perros/page/:page", component: PerrosComponent},
  { path: "perros/form", component: FormPerroComponent},
  { path: "perros/form/:id", component: FormPerroComponent},


  { path: "gatos", component: GatosComponent},
  { path: "gatos/page/:page", component: GatosComponent},
  { path: "gatos/form", component: FormGatoComponent},
  { path: "gatos/form/:id", component: FormGatoComponent},

  { path: "residencias", component: ResidenciasComponent},
  { path: "residencias/page/:page", component: ResidenciasComponent},
  { path: "residencias/form", component: FormResidenciaComponent},
  { path: "residencias/form/:id", component: FormResidenciaComponent},

  { path: "voluntarias", component: VoluntariasComponent},
  { path: "voluntarias/page/:page", component: VoluntariasComponent},
  { path: "voluntarias/form", component: FormVoluntariaComponent},
  { path: "voluntarias/form/:id", component: FormVoluntariaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
