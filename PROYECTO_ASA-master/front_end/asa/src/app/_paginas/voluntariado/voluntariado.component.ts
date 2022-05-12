import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-voluntariado',
  templateUrl: './voluntariado.component.html',
  styleUrls: ['./voluntariado.component.css']
})
export class VoluntariadoComponent implements OnInit {

  titulo1: string;
  titulo2: string;
  titulo3: string;
  texto1: string;
  texto2: string;
  texto3: string;
  texto11: string;
  lista: any[]=[];
  contenido: any[]=[];

  constructor() {

  }
  ngOnInit(): void {
    this.titulo1 = "Ser Voluntario";
    this.titulo2 = "¿Qué buscamos en nuestros voluntarios?";
    this.titulo3 = "Que puedes hacer?";


    this.texto1 = "Ser voluntario es dedicar parte de tu tiempo para ayudar a los animales, directa e indirectamente!"
    this.texto11 = " Hay muchas tareas que se pueden hacer y todas tienen su valor e importancia. Aunque lo que más nos reconforta es tener contacto directo con los animales, el objetivo final de ASA es que todos ellos acaben encontrando el hogar que se merecen, por lo que no hay que menospreciar las tareas de difusión que ayudan a encontrar esas familias que están buscando, participar en eventos que nos permiten pagar las facturas de los veterinarios, alimentos, etc. Como veis, todo es necesario y tiene la misma importancia, para nosotros y para ellos, esos pequeños a los que intentamos ayudar, y por lo que todos decidimos ser voluntarios."
    this.texto2 = "Simplemente un poquito de constancia y compromiso. Cada uno dedica el tiempo que quiere y puede. Es el voluntario el que decide qué disponibilidad tiene y cómo ayudar. Si somos realistas, ni con todo el tiempo del mundo abarcaríamos todo lo que hay que hacer, por lo que cuantos más seamos más podemos colaborar.Pero tú decides cuál es tu disponibilidad, cómo quieres ayudar y cuándo."
    this.texto3 = "Pues os contamos un poco las tareas que en principio se pueden realizar en la asociación para ayudar a los animales. Aunque seguramente nos saltemos muchas de ellas, aquí os dejamos algunas:"
    this.lista.push( "Voluntarios en las residencias de animales. ");
    this.contenido.push( "Muchos de los animales se encuentran en residencia en lo que encuentran una familia de acogida o de adopción. Y mientras encuentran un hogar disfrutan de las visitas y los cuidados de los voluntarios, además de llevarlos a sus visitas veterinarias, llevarles pienso … Como veis hay mucho por hacer y pocas manos.");
    this.lista.push( "Difundiendo a los animales en redes sociales");
    this.contenido.push("(Facebook, Twitter e Instagram), poniendo anuncios, hablando de ellos en grupos y páginas. Solo necesitas un ordenador/tablet o móvil y un poco de tiempo al día. Y aunque es una labor que está poco valorada, es de gran importancia para ellos, de esas difusiones depende que encuentren una familia que les cuide como se merecen. ¿No es el objetivo que todos buscamos?");
    this.lista.push( "Voluntarios para eventos, visitas a establecimientos colaboradores. ");
    this.contenido.push( "Ayudando en los eventos de adopción y mercadillos, así como la visita a los establecimientos colaboradores son de vital importancia para la asociación, ya que gracias a ellos podemos pagar las facturas y emprender nuevas tareas para mejorar y aportar un poquito más a esos animales que no tienen nada.");
    this.lista.push( "Voluntarios para transportes.");
    this.contenido.push( " En numerosas ocasiones necesitamos personas que puedan trasladar perros o gatos. Hay que llevarlos al veterinario, a sus casas de acogida, etc.");
    this.lista.push("Tareas administrativas:");
    this.contenido.push( "web, correos… De nada sirve que los adoptantes, acogidas, etc. contacten con la asociación si no hay tiempo ni forma para atenderlos, ¿verdad? Es una de las tareas poco valoradas pero, como otras, es de vital importancia.");

  }

}
