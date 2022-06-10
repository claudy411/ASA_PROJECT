package com.asa.CRUD.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;

import com.asa.CRUD.model.entity.Mascota;

public class AdopcionDto {
	
	private Long id;
	
	@NotNull(message="no puede estar vacío!")
	private String mascota;
	
	
	@NotNull(message="no puede estar vacío!")
	private String nombre;
	
	@NotNull(message="no puede estar vacío!")
	private String apellidos;
	
	@NotNull(message="no puede estar vacío!")
	@Email(message=" el formato no es válido!")
	private String email;

	@NotNull(message="no puede estar vacío!")
	@Size(min=9, max=9)
	private String telefono;
	
	@NotNull(message="no puede estar vacío!")
	private String direccion;

	@NotNull(message="no puede estar vacío!")
	private String ciudad;
	
	@NotNull(message="no puede estar vacío!")
	private String conocerAsa;
	
	@NotNull(message="no puede estar vacío!")
	private String contactarOtras;
	
	@NotNull(message="no puede estar vacío!")
	private Integer tamaniofamilia;
	
	@NotNull(message="no puede estar vacío!")
	private String nenes;
	
	@NotNull(message="no puede estar vacío!")
	private String acuerdo;
	
	@NotNull(message="no puede estar vacío!")
	private String cuidar;
	
	@NotNull(message="no puede estar vacío!")
	private String alergia;
	
	@NotNull(message="no puede estar vacío!")
	private String animalEnCasa;
	

	private String infoMascotaEnCasa;
	
	@NotNull(message="no puede estar vacío!")
	private String veterinarioReferencia;
	
	@NotNull(message="no puede estar vacío!")
	private String gastos;
	
	@NotNull(message="no puede estar vacío!")
	private String permisoAlquiler;
	
	@NotNull(message="no puede estar vacío!")
	private String rinconMascota;
	
	@NotNull(message="no puede estar vacío!")
	private String soloEnCasa;
	
	@NotNull(message="no puede estar vacío!")
	private String situacionSoloEnCasa;
	
	@NotNull(message="no puede estar vacío!")
	private String aMiLado;
	
	@NotNull(message="no puede estar vacío!")
	private String juegueton;
	
	@NotNull(message="no puede estar vacío!")
	private String entrenar;
	
	@NotNull(message="no puede estar vacío!")
	private String tranquilo;
	
	@NotNull(message="no puede estar vacío!")
	private String importante;
	
	@NotNull(message="no puede estar vacío!")
	private String asesoramiento;
	
	@NotNull(message="no puede estar vacío!")
	private String recoger;
	
	@NotNull(message="no puede estar vacío!")
	private String discapacidad;
	
	@NotNull(message="no puede estar vacío!")
	private String edad;
	
	@NotNull(message="no puede estar vacío!")
	private String vacaciones;
	
	@NotNull(message="no puede estar vacío!")
	private String motivo;
	
	@NotNull(message="no puede estar vacío!")
	private String esterilizacion;
	
	@NotNull(message="no puede estar vacío!")
	private String comportamiento;
	
	@NotNull(message="no puede estar vacío!")
	private String devolucion;
	
	@NotNull(message="no puede estar vacío!")
	private String tiempo;
	
	@NotNull(message="no puede estar vacío!")
	private String visita;
	
	@NotNull(message="no puede estar vacío!")
	private String pagar;
	
	private Date fecha;

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
		return mascota;
	}

	public void setMascota(String mascota) {
		this.mascota = mascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
