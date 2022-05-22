package com.asa.CRUD.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IEventoDao;
import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.ILocalizacionDao;
import com.asa.CRUD.model.entity.Evento;
import com.asa.CRUD.model.entity.Localizacion;
import com.asa.CRUD.model.services.interfaces.IEventoService;

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
