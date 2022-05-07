package com.asa.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="eventos")
public class Evento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4201748380504716352L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="no puede estar vacío!")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@NotEmpty(message="no puede estar vacío!")
	private String descripcion;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_localizacion", referencedColumnName = "id")
	private Localizacion localizacion;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="evento")
	private List<Voluntaria> voluntarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public Localizacion getLocalizacion() {
//		return localizacion;
//	}
//
//	public void setLocalizacion(Localizacion localizacion) {
//		this.localizacion = localizacion;
//	}


	
	
}
