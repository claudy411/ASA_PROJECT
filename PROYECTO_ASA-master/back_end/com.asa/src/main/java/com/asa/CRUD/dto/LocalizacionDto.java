package com.asa.CRUD.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.asa.CRUD.model.entity.EncargadoLocalizacion;
import com.asa.CRUD.model.entity.Evento;

public class LocalizacionDto {

	private Long id;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;

	@NotEmpty(message = "no puede estar vacío!")
	private String direccion;

	@NotEmpty(message = "no puede estar vacío!")
	private String localidad;
	
//	private List<EncargadoDto> encargados;



//	
//	private Evento evento;
//
//	public Evento getEvento() {
//		return evento;
//	}
//
//	public void setEvento(Evento evento) {
//		this.evento = evento;
//	}

//	public List<EncargadoDto> getEncargados() {
//		return encargados;
//	}
//
//	public void setEncargados(List<EncargadoDto> encargados) {
//		this.encargados = encargados;
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
