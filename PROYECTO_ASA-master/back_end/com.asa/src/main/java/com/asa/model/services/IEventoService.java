package com.asa.model.services;

import com.asa.model.entity.Evento;
import com.asa.model.entity.Localizacion;

public interface IEventoService extends ICRUD<Evento, Long> {

	public Localizacion getLocalizacion(Evento evento);
}
