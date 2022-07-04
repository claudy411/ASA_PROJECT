package com.asa.CRUD.model.services.interfaces;

import java.util.List;

import com.asa.CRUD.model.entity.Evento;
import com.asa.CRUD.model.entity.Localizacion;

public interface IEventoService extends ICRUD<Evento, Long> {

	public List<Localizacion> buscarPorLocalizacion(Long id);
	

	public String verFoto(Long id);
}
