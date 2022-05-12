package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IGenericDao;
import com.asa.model.IVolutariaDao;
import com.asa.model.entity.Voluntaria;
import com.asa.model.services.IVoluntariaService;


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
