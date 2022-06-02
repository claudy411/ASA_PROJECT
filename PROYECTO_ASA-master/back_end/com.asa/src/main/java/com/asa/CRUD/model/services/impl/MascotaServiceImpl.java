package com.asa.CRUD.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.dao.IMascotaDao;
import com.asa.CRUD.model.entity.Mascota;
import com.asa.CRUD.model.services.interfaces.IMascotaService;

@Service
public class MascotaServiceImpl extends CRUDImpl<Mascota, Long> implements IMascotaService  {

	@Autowired
	private IMascotaDao mascotaDao;

	@Override
	protected IGenericDao<Mascota, Long> getRepo() {
		// TODO Auto-generated method stub
		return mascotaDao;
	}

	@Override
	public List<Mascota> buscarPorTipo(String tipo) {
		// TODO Auto-generated method stub
		return mascotaDao.findByTipo(tipo);
	}

	@Override
	public List<Mascota> buscarPorSituacion(String situacion) {
		// TODO Auto-generated method stub
		return mascotaDao.findBySituacion(situacion);
	}

	@Override
	public String verFoto(Long id) {
		// TODO Auto-generated method stub
		return mascotaDao.getFoto(id);
	}
	
	
}
