package com.asa.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="voluntarias")
public class Voluntaria extends Usuarios implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -338973690140202790L;

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	@Override
	@NotEmpty(message="no puede estar vacío!")
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}
	
	@Override
	@NotEmpty(message="no puede estar vacío!")
	public String getApellido1() {
		// TODO Auto-generated method stub
		return super.getApellido1();
	}
	
	@Override
	public String getApellido2() {
		// TODO Auto-generated method stub
		return super.getApellido2();
	}
	
	@Override
	@NotEmpty(message="no puede estar vacío!")
	@Email(message=" el formato no es válido!")
	@Column(nullable = false, unique = true)
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}
	
	@Override
	@NotEmpty(message="no puede estar vacío!")
	public String getTelefono() {
		// TODO Auto-generated method stub
		return super.getTelefono();
	}
	
	@Override
	@NotEmpty(message="no puede estar vacío!")
	public String getCiudad() {
		// TODO Auto-generated method stub
		return super.getCiudad();
	}


}
