package com.asa.CRUD.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "adopciones")
public class Adopcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private String mascotaNombre;
	
	private String nombre;
	
	private String apellidos;
	
	private String email;

	@Column(length = 9)
	private String telefono;

	private String direccion;

	private String ciudad;
	
	private String conocerAsa;
	
	private String contactarOtras;
	
	private Integer tamaniofamilia;
	
	private String nenes;
	
	private String acuerdo;
	
	private String cuidar;
	
	private String alergia;
	
	private String animalEnCasa;
	
	private String infoMascotaEnCasa;
	
	private String veterinarioReferencia;
	
	private String gastos;
	
	private String permisoAlquiler;
	
	private String rinconMascota;
	
	private String soloEnCasa;
	
	private String situacionSoloEnCasa;
	
	private String aMiLado;
	
	private String juegueton;
	
	private String entrenar;
	
	private String tranquilo;
	
	private String importante;
	
	private String asesoramiento;
	
	private String recoger;
	
	private String discapacidad;
	
	private String edad;
	
	private String vacaciones;
	
	private String motivo;
	
	private String esterilizacion;
	
	private String comportamiento;
	
	private String devolucion;
	
	private String tiempo;
	
	private String visita;
	
	private String pagar;
	

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMascota() {
		return mascotaNombre;
	}

	public void setMascota(String mascota) {
		this.mascotaNombre = mascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMascotaNombre() {
		return mascotaNombre;
	}

	public void setMascotaNombre(String mascotaNombre) {
		this.mascotaNombre = mascotaNombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getConocerAsa() {
		return conocerAsa;
	}

	public void setConocerAsa(String conocerAsa) {
		this.conocerAsa = conocerAsa;
	}

	public String getContactarOtras() {
		return contactarOtras;
	}

	public void setContactarOtras(String contactarOtras) {
		this.contactarOtras = contactarOtras;
	}

	public Integer getTamaniofamilia() {
		return tamaniofamilia;
	}

	public void setTamaniofamilia(Integer tamaniofamilia) {
		this.tamaniofamilia = tamaniofamilia;
	}

	public String getNenes() {
		return nenes;
	}

	public void setNenes(String nenes) {
		this.nenes = nenes;
	}

	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getCuidar() {
		return cuidar;
	}

	public void setCuidar(String cuidar) {
		this.cuidar = cuidar;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getAnimalEnCasa() {
		return animalEnCasa;
	}

	public void setAnimalEnCasa(String animalEnCasa) {
		this.animalEnCasa = animalEnCasa;
	}

	public String getInfoMascotaEnCasa() {
		return infoMascotaEnCasa;
	}

	public void setInfoMascotaEnCasa(String infoMascotaEnCasa) {
		this.infoMascotaEnCasa = infoMascotaEnCasa;
	}

	public String getVeterinarioReferencia() {
		return veterinarioReferencia;
	}

	public void setVeterinarioReferencia(String veterinarioReferencia) {
		this.veterinarioReferencia = veterinarioReferencia;
	}

	public String getGastos() {
		return gastos;
	}

	public void setGastos(String gastos) {
		this.gastos = gastos;
	}

	public String getPermisoAlquiler() {
		return permisoAlquiler;
	}

	public void setPermisoAlquiler(String permisoAlquiler) {
		this.permisoAlquiler = permisoAlquiler;
	}

	public String getRinconMascota() {
		return rinconMascota;
	}

	public void setRinconMascota(String rinconMascota) {
		this.rinconMascota = rinconMascota;
	}

	public String getSoloEnCasa() {
		return soloEnCasa;
	}

	public void setSoloEnCasa(String soloEnCasa) {
		this.soloEnCasa = soloEnCasa;
	}

	public String getSituacionSoloEnCasa() {
		return situacionSoloEnCasa;
	}

	public void setSituacionSoloEnCasa(String situacionSoloEnCasa) {
		this.situacionSoloEnCasa = situacionSoloEnCasa;
	}

	public String getaMiLado() {
		return aMiLado;
	}

	public void setaMiLado(String aMiLado) {
		this.aMiLado = aMiLado;
	}

	public String getJuegueton() {
		return juegueton;
	}

	public void setJuegueton(String juegueton) {
		this.juegueton = juegueton;
	}

	public String getEntrenar() {
		return entrenar;
	}

	public void setEntrenar(String entrenar) {
		this.entrenar = entrenar;
	}

	public String getTranquilo() {
		return tranquilo;
	}

	public void setTranquilo(String tranquilo) {
		this.tranquilo = tranquilo;
	}

	public String getImportante() {
		return importante;
	}

	public void setImportante(String importante) {
		this.importante = importante;
	}

	public String getAsesoramiento() {
		return asesoramiento;
	}

	public void setAsesoramiento(String asesoramiento) {
		this.asesoramiento = asesoramiento;
	}

	public String getRecoger() {
		return recoger;
	}

	public void setRecoger(String recoger) {
		this.recoger = recoger;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(String vacaciones) {
		this.vacaciones = vacaciones;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getEsterilizacion() {
		return esterilizacion;
	}

	public void setEsterilizacion(String esterilizacion) {
		this.esterilizacion = esterilizacion;
	}

	public String getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}

	public String getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(String devolucion) {
		this.devolucion = devolucion;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getVisita() {
		return visita;
	}

	public void setVisita(String visita) {
		this.visita = visita;
	}

	public String getPagar() {
		return pagar;
	}

	public void setPagar(String pagar) {
		this.pagar = pagar;
	}

	
}
