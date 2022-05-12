package com.asa.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime fecha;

	private String descripcion;

	private String nombre;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_localizacion", referencedColumnName = "id")
	private Localizacion localizacion;

	@ManyToMany
	@JoinTable(name = "eventos_voluntarias", joinColumns = @JoinColumn(name = "id_evento"), inverseJoinColumns = @JoinColumn(name = "id_voluntaria"))
	private List<Voluntaria> voluntarios;

	@OneToOne(mappedBy = "evento")
	private Horario horario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
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

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public List<Voluntaria> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntaria> voluntarios) {
		this.voluntarios = voluntarios;
	}

}
