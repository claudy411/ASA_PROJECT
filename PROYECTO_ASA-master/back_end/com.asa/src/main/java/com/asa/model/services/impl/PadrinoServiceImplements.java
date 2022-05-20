package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IGenericDao;
import com.asa.model.IPadrinoDao;
import com.asa.model.entity.Padrino;
import com.asa.model.services.IPadrinoService;

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
