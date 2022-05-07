package com.asa.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="acogidas")
public class Acogida {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
	@NotEmpty(message="no puede estar vacío!")
	private String apellido1;
	

	private String apellido2;
	
	@NotEmpty(message="no puede estar vacío!")
	@Email(message=" el formato no es válido!")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotEmpty(message="no puede estar vacío!")
	private String telefono;

	@NotEmpty(message="no puede estar vacío!")
	private String ciudad;
	
	@ManyToMany
	@JoinTable(
	  name = "adoptante_mascota", 
	  joinColumns = @JoinColumn(name = "id_adoptante"), 
	  inverseJoinColumns = @JoinColumn(name = "id_mascota"))
	private List<Mascota>  mascotas;

	
	@ManyToMany
	@JoinTable(
	  name = "acogida_mascota", 
	  joinColumns = @JoinColumn(name = "id_acogida"), 
	  inverseJoinColumns = @JoinColumn(name = "id_mascota"))
	private List<Mascota>  mascotass;


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


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public List<Mascota> getMascotas() {
		return mascotas;
	}


	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}


	public List<Mascota> getMascotass() {
		return mascotass;
	}


	public void setMascotass(List<Mascota> mascotass) {
		this.mascotass = mascotass;
	}

	
}
