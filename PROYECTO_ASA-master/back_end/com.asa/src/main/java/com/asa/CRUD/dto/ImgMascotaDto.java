package com.asa.CRUD.dto;

import javax.validation.constraints.NotNull;

import com.asa.CRUD.model.entity.Mascota;

public class ImgMascotaDto {

	private Long id;
	@NotNull
	private String url;
	
//	private Mascota mascota;
//	
//	public Mascota getMascota() {
//		return mascota;
//	}
//
//	public void setMascota(Mascota mascota) {
//		this.mascota = mascota;
//	}

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

	
}
