package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.IVolutariaDao;
import com.asa.CRUD.model.entity.Voluntaria;
import com.asa.CRUD.model.services.interfaces.IVoluntariaService;


@Service
public class VoluntariaServiceImpl extends CRUDImpl<Voluntaria, Long> implements IVoluntariaService {

	@Autowired
	private IVolutariaDao voluntariaDao;

	@Override
	protected IGenericDao<Voluntaria, Long> getRepo() {
		// TODO Auto-generated method stub
		return voluntariaDao;
	}
	
	
}
