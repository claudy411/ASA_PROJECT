import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nosotros',
  templateUrl: './nosotros.component.html',
  styleUrls: ['./nosotros.component.css']
})
export class NosotrosComponent implements OnInit {

  titulo:string;
  subtitulo:string;
  parrafo1:string;
  parrafo2:string;
  parrafo3:string;
  logo:string;

  constructor() {
    this.logo="ASA  Asociación por la Sensibilidad Animal";
    this.titulo="¿Qué es ASA?";
    this.subtitulo="Trabajamos para fomentar la tenencia responsable y la empatía y respeto ante cualquier forma de vida."
    this.parrafo1="Es una organización nacional sin ánimo de lucro, nuestro único beneficio es la satisfacción de conseguir cambiar las vidas de algunos animales. Sabemos que no podemos llegar a todos, es inabarcable la cantidad de animales abandonados y maltratados que existen, pero lo importante es centrarnos en cómo podemos ayudar a aquellos a los que sí podemos llegar y, poco a poco, con mucho esfuerzo y, sobre todo, con vuestro apoyo, podemos ir creciendo y ayudar a muchos más."
   this.parrafo2="Una parte importante de nuestra familia son nuestros pequeños peludos, ellos son de una forma u otra los que nos empujaron a crear ASA – Asociación por la Sensibilidad Animal. Queremos ayudar a aquellos que, a diferencia de los nuestros, no tienen familia y, en ocasiones, su vida pende de un hilo. Sabemos que la tarea es difícil y que no siempre se consigue, pero cuando un pequeño encuentra su hogar, el calor de una mano amiga, hace que la lucha y el sufrimiento merezcan la pena."
    this.parrafo3="Así nacemos, de la ilusión de un grupo de personas con ganas de trabajar para y por ellos. Empezamos poco a poco, pero trabajando duro con la esperanza de poder llegar a rescatar a un gran número de animales. Gracias al esfuerzo de los voluntarios y a los amigos que nos hemos ido encontrando en el camino cada día son muchos los animales que han encontrado familia."
  }

  ngOnInit(): void {
  }

}
