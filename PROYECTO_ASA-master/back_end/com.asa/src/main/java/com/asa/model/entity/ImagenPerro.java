package com.asa.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "imagenes_perros")
public class ImagenPerro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no puede estar vacío!")
	@Column(nullable = false)
	private String url;

	@NotNull(message = "no puede estar vacío!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perro", nullable = false)
	private Perro perro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Perro getPerro() {
		return perro;
	}

	public void setPerro(Perro perro) {
		this.perro = perro;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6561913657378546790L;

}
