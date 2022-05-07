package com.asa.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="localizaciones")
public class Localizacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
	@NotEmpty(message="no puede estar vacío!")
	private String calle;
	
	@NotEmpty(message="no puede estar vacío!")
	private String numero;
	
	@NotEmpty(message="no puede estar vacío!")
	private String cp;
	
	@NotEmpty(message="no puede estar vacío!")
	private String localidad;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="localizacion")
	private List<EncargadoLocalizacion> encargados;
	
	@OneToOne(mappedBy = "localizacion")
	private Evento evento;

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



	public String getCalle() {
		return calle;
	}



	public void setCalle(String calle) {
		this.calle = calle;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getCp() {
		return cp;
	}



	public void setCp(String cp) {
		this.cp = cp;
	}



	public String getLocalidad() {
		return localidad;
	}



	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



//	public EncargadoLocalizacion getEncargado() {
//		return encargado;
//	}
//
//
//
//	public void setEncargado(EncargadoLocalizacion encargado) {
//		this.encargado = encargado;
//	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -2331125274599473619L;

}
