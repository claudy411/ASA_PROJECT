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
	

	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	

	@Column(nullable = false)
	private String nombre;
	
	

	@Column(name="f_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fNacimiento;
	
	@Column(name="f_entrada")
	@Temporal(TemporalType.DATE)
	private Date fEntrada;
	

	private String raza;
	

	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	

	private String caracter;//caracter
	
	@Enumerated(EnumType.STRING)
	private Size size;
	

	@Enumerated(EnumType.STRING)
	private Situacion situacion;//en residencia, en acogida o adoptado

	@ManyToOne
	@JoinColumn(name="id_adoptante",nullable = false,foreignKey = @ForeignKey(name="FK_adoptantes_mascotas"))
	private Adoptante adoptante;

	@ManyToOne
	@JoinColumn(name="id_acogida",nullable = false,foreignKey = @ForeignKey(name="FK_acogidas_mascotas"))
	private Acogida acogida;
	
	@ManyToOne	
	@JoinColumn(name = "residencia_id",nullable = false)
	private Residencia residencia;
	
	@OneToMany( mappedBy="fmascota",cascade=CascadeType.ALL)
	private List<ImagenMascota> fotos;
	
	@ManyToMany(mappedBy = "pMascotas",cascade = CascadeType.PERSIST)
	private List<Padrino> padrinos;
	
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


	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
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


	public Residencia getResidencia() {
		return residencia;
	}


	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}
	


	

}