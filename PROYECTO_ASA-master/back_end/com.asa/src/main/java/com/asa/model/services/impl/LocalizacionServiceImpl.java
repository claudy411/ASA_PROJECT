package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IEventoDao;
import com.asa.model.IGenericDao;
import com.asa.model.ILocalizacionDao;
import com.asa.model.entity.Evento;
import com.asa.model.entity.Localizacion;
import com.asa.model.services.ILocalizacionService;

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
