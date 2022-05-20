package com.asa.CRUD.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.IAdoptanteDao;
import com.asa.CRUD.model.IGenericDao;
import com.asa.CRUD.model.entity.Adoptante;
import com.asa.CRUD.model.services.IAdoptanteService;

@Service
public class AdoptanteServiceImpl extends CRUDImpl<Adoptante, Long> implements IAdoptanteService {

	@Autowired
	private IAdoptanteDao adoptanteDao;

	@Override
	protected IGenericDao<Adoptante, Long> getRepo() {
		// TODO Auto-generated method stub
		return adoptanteDao;
	}
	
	
}
