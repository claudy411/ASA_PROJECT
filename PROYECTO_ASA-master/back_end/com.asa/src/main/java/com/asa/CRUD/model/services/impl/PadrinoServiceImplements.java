package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.IPadrinoDao;
import com.asa.CRUD.model.entity.Padrino;
import com.asa.CRUD.model.services.interfaces.IPadrinoService;

@Service
public class PadrinoServiceImplements extends CRUDImpl<Padrino, Long> implements IPadrinoService {

	@Autowired
	private IPadrinoDao dao;
	
	@Override
	protected IGenericDao<Padrino, Long> getRepo() {
		// TODO Auto-generated method stub
		return dao;
	}

}
