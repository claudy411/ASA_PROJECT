package com.asa.CRUD.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.asa.CRUD.enumerados.Situacion;
import com.asa.CRUD.enumerados.Size;
import com.asa.CRUD.enumerados.Tipo;

public class MascotaDto {
	
	
	private Long id;
	
	@NotNull(message="no puede estar vacío!")
	private Tipo tipo;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
	
	@NotNull(message="no puede estar vacío!")
	private LocalDateTime fNacimiento;
	
	@NotNull(message="no puede estar vacío!")
	private LocalDateTime fEntrada;
	
	@NotEmpty(message="no puede estar vacío!")
	private String raza;
	
	@NotEmpty(message="no puede estar vacío!")
	private String sexo;
	
	@NotEmpty(message="no puede estar vacío!")
	private String caracter;//caracter
	
	private Size size;
	
	@NotNull(message="no puede estar vacío!")
	private Situacion situacion;//en residencia, en acogida o adoptado
	
	private String fotoPerfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(LocalDateTime fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public LocalDateTime getfEntrada() {
		return fEntrada;
	}

	public void setfEntrada(LocalDateTime fEntrada) {
		this.fEntrada = fEntrada;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Situacion getSituacion() {
		return situacion;
	}

	public void setSituacion(Situacion situacion) {
		this.situacion = situacion;
	}
	
	

}
