package com.asa.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EventoDto {

	private Long id;

	@NotNull(message = "no puede estar vacío!")
	private LocalDateTime fecha;

	@NotEmpty(message = "no puede estar vacío!")
	private String descripcion;

	@NotEmpty(message = "no puede estar vacío!")
	private String nombre;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_localizacion", referencedColumnName = "id")
//	private Localizacion localizacion;
//	
//	@ManyToMany
//	@JoinTable(
//	  name = "eventos_voluntarias", 
//	  joinColumns = @JoinColumn(name = "id_evento"), 
//	  inverseJoinColumns = @JoinColumn(name = "id_voluntaria"))
//	private List<Voluntaria> voluntarios;
//	
//	@OneToOne(mappedBy = "evento")
//	private Horario horario;

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

}
