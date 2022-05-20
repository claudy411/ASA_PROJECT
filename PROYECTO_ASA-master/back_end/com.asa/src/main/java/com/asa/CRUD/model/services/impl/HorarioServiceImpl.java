package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.IGenericDao;
import com.asa.CRUD.model.IHorarioDao;
import com.asa.CRUD.model.entity.Horario;
import com.asa.CRUD.model.services.IHorarioService;

@Service
public class HorarioServiceImpl extends CRUDImpl<Horario, Long> implements IHorarioService {

	@Autowired
	IHorarioDao horarioDao;
	
	@Override
	protected IGenericDao<Horario, Long> getRepo() {
		// TODO Auto-generated method stub
		return horarioDao;
	}

	
}
