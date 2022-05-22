package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IEventoDao;
import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.ILocalizacionDao;
import com.asa.CRUD.model.entity.Evento;
import com.asa.CRUD.model.entity.Localizacion;
import com.asa.CRUD.model.services.interfaces.ILocalizacionService;

@Service
public class LocalizacionServiceImpl  extends CRUDImpl<Localizacion, Long> implements ILocalizacionService {
	
	@Autowired
	private ILocalizacionDao locDao;
	
	

	@Override
	protected IGenericDao<Localizacion, Long> getRepo() {
		// TODO Auto-generated method stub
		return locDao;
	}



	
}
