import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header_footer/header/header.component';
import { FooterComponent } from './header_footer/footer/footer.component';
import { ImgPerrosComponent } from './crud/img-perros/img-perros.component';
import { ImgGatosComponent } from './crud/img-gatos/img-gatos.component';
import { PaginadorAdoptanteComponent } from './crud/adoptantes/paginador-adoptante/paginador-adoptante.component';
import { FormsModule } from '@angular/forms';
import { AdoptanteServiceService } from './crud/adoptantes/adoptante-service.service';
import { HttpClientModule } from '@angular/common/http';
import { PagEncargadoComponent } from './crud/encargados-localizaciones/pag-encargado/pag-encargado.component';
import { EncargadoService } from './crud/encargados-localizaciones/encargado.service';
import { PagEventoComponent } from './crud/eventos/pag-evento/pag-evento.component';
import { PagGatoComponent } from './crud/gatos/pag-gato/pag-gato.component';
import { PagLocalizacionComponent } from './crud/localizaciones/pag-localizacion/pag-localizacion.component';
import { PagPerroComponent } from './crud/perros/pag-perro/pag-perro.component';
import { PagResidenciaComponent } from './crud/residencias/pag-residencia/pag-residencia.component';
import { PagVoluntariaComponent } from './crud/voluntarias/pag-voluntaria/pag-voluntaria.component';
import { EventoService } from './crud/eventos/evento.service';
import { GatoService } from './crud/gatos/gato.service';
import { LocalizacionService } from './crud/localizaciones/localizacion.service';
import { PerroService } from './crud/perros/perro.service';
import { ResidenciaService } from './crud/residencias/residencia.service';
import { VoluntariaService } from './crud/voluntarias/voluntaria.service';
import { DetalleComponent } from './crud/adoptantes/detalle/detalle.component';
import { ListaComponent } from './crud/adoptantes/lista/lista.component';
import { EditarComponent } from './crud/adoptantes/editar/editar.component';
import { NuevoComponent } from './crud/adoptantes/nuevo/nuevo.component';
import { EncargadoNuevoComponent } from './crud/encargados-localizaciones/encargado-nuevo/encargado-nuevo.component';
import { EncargadoDetalleComponent } from './crud/encargados-localizaciones/encargado-detalle/encargado-detalle.component';
import { EncargadoEditarComponent } from './crud/encargados-localizaciones/encargado-editar/encargado-editar.component';
import { EncargadoListaComponent } from './crud/encargados-localizaciones/encargado-lista/encargado-lista.component';
import { EventoNuevoComponent } from './crud/eventos/evento-nuevo/evento-nuevo.component';
import { EventoDetalleComponent } from './crud/eventos/evento-detalle/evento-detalle.component';
import { EventoEditarComponent } from './crud/eventos/evento-editar/evento-editar.component';
import { EventoListaComponent } from './crud/eventos/evento-lista/evento-lista.component';
import { GatoNuevoComponent } from './crud/gatos/gato-nuevo/gato-nuevo.component';
import { GatoEditarComponent } from './crud/gatos/gato-editar/gato-editar.component';
import { GatoDetalleComponent } from './crud/gatos/gato-detalle/gato-detalle.component';
import { GatoListaComponent } from './crud/gatos/gato-lista/gato-lista.component';
import { LocalizacionNuevaComponent } from './crud/localizaciones/localizacion-nueva/localizacion-nueva.component';
import { LocalizacionEditarComponent } from './crud/localizaciones/localizacion-editar/localizacion-editar.component';
import { LocalizacionDetalleComponent } from './crud/localizaciones/localizacion-detalle/localizacion-detalle.component';
import { LocalizacionListaComponent } from './crud/localizaciones/localizacion-lista/localizacion-lista.component';
import { ResidenciaNuevaComponent } from './crud/residencias/residencia-nueva/residencia-nueva.component';
import { ResidenciaEditarComponent } from './crud/residencias/residencia-editar/residencia-editar.component';
import { ResidenciaListaComponent } from './crud/residencias/residencia-lista/residencia-lista.component';
import { ResidenciaDetalleComponent } from './crud/residencias/residencia-detalle/residencia-detalle.component';
import { VoluntariaNuevoComponent } from './crud/voluntarias/voluntaria-nuevo/voluntaria-nuevo.component';
import { VoluntariaListaComponent } from './crud/voluntarias/voluntaria-lista/voluntaria-lista.component';
import { VoluntariaEditarComponent } from './crud/voluntarias/voluntaria-editar/voluntaria-editar.component';
import { VoluntariaDetalleComponent } from './crud/voluntarias/voluntaria-detalle/voluntaria-detalle.component';
import { PerroNuevoComponent } from './crud/perros/perro-nuevo/perro-nuevo.component';
import { PerroDetalleComponent } from './crud/perros/perro-detalle/perro-detalle.component';
import { PerroEditarComponent } from './crud/perros/perro-editar/perro-editar.component';
import { PerroListaComponent } from './crud/perros/perro-lista/perro-lista.component';
import { LoginComponent } from './security/auth/login/login.component';
import { RegistroComponent } from './security/auth/registro/registro.component';
import { IndexComponent } from './security/index/index.component';
import { MenuComponent } from './security/menu/menu.component';
import { interceptorProvider } from './security/interceptors/interceptor.service';
import { InicioComponent } from './inicio/inicio.component';
import { DashboardComponent } from './registrados/dashboard.component';
import { ModalService } from './services/modal.service';
import { animate } from '@angular/animations';
import { NosotrosComponent } from './_paginas/nosotros/nosotros.component';
import { AdoptaComponent } from './_paginas/adopta/adopta.component';
import { AcogeComponent } from './_paginas/acoge/acoge.component';
import { VoluntariadoComponent } from './_paginas/voluntariado/voluntariado.component';
import { EventosComponent } from './_paginas/eventos/eventos.component';
import { ContactoComponent } from './_paginas/contacto/contacto.component';
import { FormularioComponent } from './_paginas/formulario/formulario.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ImgPerrosComponent,
    ImgGatosComponent,
    PaginadorAdoptanteComponent,
    PagEncargadoComponent,
    PagEventoComponent,
    PagGatoComponent,
    PagLocalizacionComponent,
    PagPerroComponent,
    PagResidenciaComponent,
    PagVoluntariaComponent,
    DetalleComponent,
    ListaComponent,
    EditarComponent,
    NuevoComponent,
    EncargadoNuevoComponent,
    EncargadoDetalleComponent,
    EncargadoEditarComponent,
    EncargadoListaComponent,
    EventoNuevoComponent,
    EventoDetalleComponent,
    EventoEditarComponent,
    EventoListaComponent,
    GatoNuevoComponent,
    GatoEditarComponent,
    GatoDetalleComponent,
    GatoListaComponent,
    LocalizacionNuevaComponent,
    LocalizacionEditarComponent,
    LocalizacionDetalleComponent,
    LocalizacionListaComponent,
    ResidenciaNuevaComponent,
    ResidenciaEditarComponent,
    ResidenciaListaComponent,
    ResidenciaDetalleComponent,
    VoluntariaNuevoComponent,
    VoluntariaListaComponent,
    VoluntariaEditarComponent,
    VoluntariaDetalleComponent,
    PerroNuevoComponent,
    PerroDetalleComponent,
    PerroEditarComponent,
    PerroListaComponent,
    LoginComponent,
    RegistroComponent,
    IndexComponent,
    MenuComponent,
    InicioComponent,
    DashboardComponent,
    NosotrosComponent,
    AdoptaComponent,
    AcogeComponent,
    VoluntariadoComponent,
    EventosComponent,
    ContactoComponent,
    FormularioComponent

  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    BrowserAnimationsModule, //esto no es necesario me quedo con el swal
    ToastrModule.forRoot()
    
  ],
  providers: [
    AdoptanteServiceService,
    EncargadoService,
    EventoService,
    GatoService,
    LocalizacionService,
    PerroService,
    ResidenciaService,
    VoluntariaService,
    interceptorProvider,
    ModalService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
