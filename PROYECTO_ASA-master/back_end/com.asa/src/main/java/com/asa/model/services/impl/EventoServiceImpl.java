package com.asa.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IEventoDao;
import com.asa.model.IGenericDao;
import com.asa.model.ILocalizacionDao;
import com.asa.model.entity.Evento;
import com.asa.model.entity.Localizacion;
import com.asa.model.services.IEventoService;

@Service
public class EventoServiceImpl extends CRUDImpl<Evento, Long> implements IEventoService{
	
	@Autowired
	private IEventoDao eventoDao;
	


	@Override
	protected IGenericDao<Evento, Long> getRepo() {
		// TODO Auto-generated method stub
		return eventoDao;
	}


}
