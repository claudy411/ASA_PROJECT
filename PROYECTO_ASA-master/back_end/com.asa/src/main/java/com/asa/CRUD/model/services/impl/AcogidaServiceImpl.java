package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.IAcogidaDao;
import com.asa.CRUD.model.IGenericDao;
import com.asa.CRUD.model.entity.Acogida;
import com.asa.CRUD.model.services.IAcogidaService;

@Service
public class AcogidaServiceImpl extends CRUDImpl<Acogida, Long> implements IAcogidaService {

	@Autowired
	private IAcogidaDao acogidaDao;

	@Override
	protected IGenericDao<Acogida, Long> getRepo() {
		// TODO Auto-generated method stub
		return acogidaDao;
	}
	
	
}
