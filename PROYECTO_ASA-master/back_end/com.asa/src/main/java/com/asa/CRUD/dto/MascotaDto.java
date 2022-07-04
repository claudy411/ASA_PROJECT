package com.asa.CRUD.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.asa.CRUD.model.entity.Acogida;
import com.asa.CRUD.model.entity.Adoptante;
import com.asa.CRUD.model.entity.Padrino;
import com.asa.CRUD.model.entity.Residencia;

public class MascotaDto {
	
	
	private Long id;
	
	@NotNull(message="no puede estar vacío!")
	private String tipo;
	
	@NotEmpty(message="no puede estar vacío!")
	private String nombre;
	
	
	@NotNull(message="no puede estar vacío!")
	private Date fNacimiento;
	
	@NotNull(message="no puede estar vacío!")
	private Date fEntrada;
	
	@NotEmpty(message="no puede estar vacío!")
	private String raza;
	
	@NotEmpty(message="no puede estar vacío!")
	private String sexo;
	
	@NotEmpty(message="no puede estar vacío!")
	private String caracter;//caracter
	
	private String size;
	
	@NotNull(message="no puede estar vacío!")
	private String situacion;//en residencia, en acogida o adoptado
	
	private String fotoPerfil;
	
	private Adoptante adoptante;
	
	private Acogida acogida;
	
	private Residencia residencia;
	
	private String historia;



	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public Adoptante getAdoptante() {
		return adoptante;
	}

	public void setAdoptante(Adoptante adoptante) {
		this.adoptante = adoptante;
	}

	public Acogida getAcogida() {
		return acogida;
	}

	public void setAcogida(Acogida acogida) {
		this.acogida = acogida;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}


//
//	public List<ImagenMascota> getFotos() {
//		return fotos;
//	}
//
//	public void setFotos(List<ImagenMascota> fotos) {
//		this.fotos = fotos;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public void setfEntrada(Date fEntrada) {
		this.fEntrada = fEntrada;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	
	

}
