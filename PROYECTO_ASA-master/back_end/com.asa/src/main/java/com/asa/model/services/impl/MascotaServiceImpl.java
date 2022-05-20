package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IGenericDao;
import com.asa.model.IMascotaDao;
import com.asa.model.entity.Mascota;
import com.asa.model.services.IMascotaService;

@Service
public class MascotaServiceImpl extends CRUDImpl<Mascota, Long> implements IMascotaService  {

	@Autowired
	private IMascotaDao mascotaDao;

	@Override
	protected IGenericDao<Mascota, Long> getRepo() {
		// TODO Auto-generated method stub
		return mascotaDao;
	}
	
	
}