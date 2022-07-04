package com.asa.CRUD.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "localizaciones")
public class Localizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String direccion;

	private String localidad;

//	@OneToMany(mappedBy = "localizacion",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private List<EncargadoLocalizacion> encargados;

	
//									los eventos son varios en la misma localizacion arreglarlo!!!
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="id_evento")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Evento evento;
//
//	public List<EncargadoLocalizacion> getEncargados() {
//		return encargados;
//	}
//
//	public void setEncargados(List<EncargadoLocalizacion> encargados) {
//		this.encargados = encargados;
//	}
//
//	public Evento getEvento() {
//		return evento;
//	}
//
//	public void setEvento(Evento evento) {
//		this.evento = evento;
//	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



}
