package com.asa.CRUD.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.asa.CRUD.enumerados.Sexo;
import com.asa.CRUD.enumerados.Situacion;
import com.asa.CRUD.enumerados.Size;
import com.asa.CRUD.enumerados.Tipo;

@Entity
@Table(name="mascotas")
public class Mascota {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	
	@Column(nullable = false)
	private String nombre;

	@Column(name="f_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fNacimiento;
	
	@Column(name="f_entrada")
	@Temporal(TemporalType.DATE)
	private Date fEntrada;
	
	private String raza;

	private String sexo;	

	private String caracter;//caracter

	private String size;

	private String situacion;//en residencia, en acogida o adoptado
	
	private String fotoPerfil;
	
	@Column(length = 800)
	private String historia;
	
	

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	@ManyToOne
	@JoinColumn(name="id_adoptante",nullable = true,foreignKey = @ForeignKey(name="FK_adoptantes_mascotas"))
	private Adoptante adoptante;

	@ManyToOne
	@JoinColumn(name="id_acogida",nullable = true,foreignKey = @ForeignKey(name="FK_acogidas_mascotas"))
	private Acogida acogida;
	
	@ManyToOne
	@JoinColumn(name = "residencia_id",nullable = true)
	private Residencia residencia;

//	@OneToMany()
//	@JoinColumn(name="id_mascota",nullable = true)
//	private List<Padrino> padrinos;
	
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
//
//	public List<Padrino> getPadrinos() {
//		return padrinos;
//	}
//
//	public void setPadrinos(List<Padrino> padrinos) {
//		this.padrinos = padrinos;
//	}
//	

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

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	

}
