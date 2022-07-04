package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.IHorarioDao;
import com.asa.CRUD.model.entity.Horario;
import com.asa.CRUD.model.services.interfaces.IHorarioService;

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
