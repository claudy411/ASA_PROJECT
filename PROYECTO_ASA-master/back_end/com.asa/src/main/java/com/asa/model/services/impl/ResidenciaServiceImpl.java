package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IGenericDao;
import com.asa.model.IResidenciaDao;
import com.asa.model.entity.Residencia;
import com.asa.model.services.IResidenciaService;

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
