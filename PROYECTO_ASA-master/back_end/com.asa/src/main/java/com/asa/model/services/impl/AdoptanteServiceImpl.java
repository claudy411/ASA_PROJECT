package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IAdoptanteDao;
import com.asa.model.IGenericDao;
import com.asa.model.entity.Adoptante;
import com.asa.model.services.IAdoptanteService;

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
