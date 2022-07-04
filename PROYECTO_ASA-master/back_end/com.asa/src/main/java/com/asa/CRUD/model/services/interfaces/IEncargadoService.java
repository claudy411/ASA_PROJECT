package com.asa.CRUD.model.services.interfaces;

import java.util.List;

import com.asa.CRUD.model.entity.EncargadoLocalizacion;

public interface IEncargadoService extends ICRUD<EncargadoLocalizacion, Long> {

	
	public List<EncargadoLocalizacion> buscarPorLocalizacion(Long id);
}
