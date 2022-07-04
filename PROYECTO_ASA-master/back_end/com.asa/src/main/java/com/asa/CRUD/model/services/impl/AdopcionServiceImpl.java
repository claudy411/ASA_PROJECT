package com.asa.CRUD.model.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IAdopcionDao;
import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.entity.Adopcion;
import com.asa.CRUD.model.services.interfaces.IAdopcionService;

@Service
public class AdopcionServiceImpl extends CRUDImpl<Adopcion, Long> implements IAdopcionService {
	
	@Autowired
	private IAdopcionDao dao;

	@Override
	protected IGenericDao<Adopcion, Long> getRepo() {
		// TODO Auto-generated method stub
		return dao;
	}


}
