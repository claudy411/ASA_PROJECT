package com.asa.model.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="perros")

public class Perro implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacío!")
	@Column(nullable = false)
	private String nombre;
	
	
	@NotNull(message="no puede estar vacío!")
	@Column(name="f_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fNacimiento;
	
	@Column(name="f_entrada")
	@NotNull(message="no puede estar vacío!")
	@Temporal(TemporalType.DATE)
	private Date fEntrada;
	
	@NotEmpty(message="no puede estar vacío!")
	private String raza;
	
	@NotEmpty(message="no puede estar vacío!")
	private String size;
	
	@NotEmpty(message="no puede estar vacío!")
	private String sexo;
	
	
	@NotEmpty(message="no puede estar vacío!")
	private String descripcion;//caracter
	
	@NotEmpty(message="no puede estar vacío!")
	private String situacion;//en residencia, en acogida o adoptado
	
//
	
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name = "residencia_id")
	
	private Residencia residencia;
	
	//falta id_acogida

	

	public Long getId() {
		return id;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
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

	public Date getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public Date getfEntrada() {
		return fEntrada;
	}

	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setfEntrada(Date fEntrada) {
		this.fEntrada = fEntrada;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5725563815572145689L;
}
