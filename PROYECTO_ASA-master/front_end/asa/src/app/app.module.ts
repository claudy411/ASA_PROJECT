import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header_footer/header/header.component';
import { FooterComponent } from './header_footer/footer/footer.component';
import { AdoptantesComponent } from './crud/adoptantes/adoptantes.component';
import { EncargadosLocalizacionesComponent } from './crud/encargados-localizaciones/encargados-localizaciones.component';
import { EventosComponent } from './crud/eventos/eventos.component';
import { GatosComponent } from './crud/gatos/gatos.component';
import { ImgPerrosComponent } from './crud/img-perros/img-perros.component';
import { ImgGatosComponent } from './crud/img-gatos/img-gatos.component';
import { LocalizacionesComponent } from './crud/localizaciones/localizaciones.component';
import { PerrosComponent } from './crud/perros/perros.component';
import { ResidenciasComponent } from './crud/residencias/residencias.component';
import { VoluntariasComponent } from './crud/voluntarias/voluntarias.component';
import { FormAdoptanteComponent } from './crud/adoptantes/form-adoptante/form-adoptante.component';
import { PaginadorAdoptanteComponent } from './crud/adoptantes/paginador-adoptante/paginador-adoptante.component';
import { FormsModule } from '@angular/forms';
import { AdoptanteServiceService } from './crud/adoptantes/adoptante-service.service';
import { HttpClientModule } from '@angular/common/http';
import { FormEncargadoComponent } from './crud/encargados-localizaciones/form-encargado/form-encargado.component';
import { PagEncargadoComponent } from './crud/encargados-localizaciones/pag-encargado/pag-encargado.component';
import { EncargadoService } from './crud/encargados-localizaciones/encargado.service';
import { FormEventoComponent } from './crud/eventos/form-evento/form-evento.component';
import { PagEventoComponent } from './crud/eventos/pag-evento/pag-evento.component';
import { FormGatoComponent } from './crud/gatos/form-gato/form-gato.component';
import { PagGatoComponent } from './crud/gatos/pag-gato/pag-gato.component';
import { FormLocalizacionComponent } from './crud/localizaciones/form-localizacion/form-localizacion.component';
import { PagLocalizacionComponent } from './crud/localizaciones/pag-localizacion/pag-localizacion.component';
import { FormPerroComponent } from './crud/perros/form-perro/form-perro.component';
import { PagPerroComponent } from './crud/perros/pag-perro/pag-perro.component';
import { FormResidenciaComponent } from './crud/residencias/form-residencia/form-residencia.component';
import { PagResidenciaComponent } from './crud/residencias/pag-residencia/pag-residencia.component';
import { FormVoluntariaComponent } from './crud/voluntarias/form-voluntaria/form-voluntaria.component';
import { PagVoluntariaComponent } from './crud/voluntarias/pag-voluntaria/pag-voluntaria.component';
import { EventoService } from './crud/eventos/evento.service';
import { GatoService } from './crud/gatos/gato.service';
import { LocalizacionService } from './crud/localizaciones/localizacion.service';
import { PerroService } from './crud/perros/perro.service';
import { ResidenciaService } from './crud/residencias/residencia.service';
import { VoluntariaService } from './crud/voluntarias/voluntaria.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AdoptantesComponent,
    EncargadosLocalizacionesComponent,
    EventosComponent,
    GatosComponent,
    ImgPerrosComponent,
    ImgGatosComponent,
    LocalizacionesComponent,
    PerrosComponent,
    ResidenciasComponent,
    VoluntariasComponent,
    FormAdoptanteComponent,
    PaginadorAdoptanteComponent,
    FormEncargadoComponent,
    PagEncargadoComponent,
    FormEventoComponent,
    PagEventoComponent,
    FormGatoComponent,
    PagGatoComponent,
    FormLocalizacionComponent,
    PagLocalizacionComponent,
    FormPerroComponent,
    PagPerroComponent,
    FormResidenciaComponent,
    PagResidenciaComponent,
    FormVoluntariaComponent,
    PagVoluntariaComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ],
  providers: [
    AdoptanteServiceService,
    EncargadoService,
    EventoService,
    GatoService,
    LocalizacionService,
    PerroService,
    ResidenciaService,
    VoluntariaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
