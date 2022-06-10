package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IContactoDao;
import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.entity.Contacto;
import com.asa.CRUD.model.services.interfaces.IContactoService;
@Service
public class ContactoServiceImpl extends CRUDImpl<Contacto, Long> implements IContactoService{
	
	@Autowired
	IContactoDao dao;

	@Override
	protected IGenericDao<Contacto, Long> getRepo() {
		// TODO Auto-generated method stub
		return dao;
	}

}
