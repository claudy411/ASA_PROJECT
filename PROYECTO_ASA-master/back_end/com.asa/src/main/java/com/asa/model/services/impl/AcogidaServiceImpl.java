package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IAcogidaDao;
import com.asa.model.IGenericDao;
import com.asa.model.entity.Acogida;
import com.asa.model.services.IAcogidaService;

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
