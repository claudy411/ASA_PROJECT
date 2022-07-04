package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.IResidenciaDao;
import com.asa.CRUD.model.entity.Residencia;
import com.asa.CRUD.model.services.interfaces.IResidenciaService;

@Service
public class ResidenciaServiceImpl extends CRUDImpl<Residencia, Long> implements IResidenciaService   {
	
	@Autowired
	private IResidenciaDao residenciaDao;

	@Override
	protected IGenericDao<Residencia, Long> getRepo() {
		// TODO Auto-generated method stub
		return residenciaDao;
	}

	

	
}
