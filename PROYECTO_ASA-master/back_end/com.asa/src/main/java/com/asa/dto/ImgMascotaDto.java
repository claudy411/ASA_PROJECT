package com.asa.dto;

import com.asa.model.entity.Mascota;

public class ImgMascotaDto {

	private Long id;

	private Mascota mascota;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

}
