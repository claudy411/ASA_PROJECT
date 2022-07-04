package com.asa.CRUD.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.CRUD.model.dao.IEncargadoLocalizacionDao;
import com.asa.CRUD.model.dao.IGenericDao;
import com.asa.CRUD.model.entity.EncargadoLocalizacion;
import com.asa.CRUD.model.services.interfaces.IEncargadoService;

@Service
public class EncargadoLocalizacionServiceImpl extends CRUDImpl<EncargadoLocalizacion, Long> implements IEncargadoService {

	@Autowired
	private IEncargadoLocalizacionDao encargadoDao;

	@Override
	protected IGenericDao<EncargadoLocalizacion, Long> getRepo() {
		// TODO Auto-generated method stub
		return encargadoDao;
	}

	@Override
	public List<EncargadoLocalizacion> buscarPorLocalizacion(Long id) {
		// TODO Auto-generated method stub
		return encargadoDao.findByLocalizacion(id);
	}
	
	
//	@Override
//	@Transactional(readOnly=true)
//	public List<EncargadoLocalizacion> findAll() {
//		
//		return encargadoDao.findAll();
//	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public Page<EncargadoLocalizacion> findAll(Pageable pageable) {
//		
//		return encargadoDao.findAll(pageable);
//	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public EncargadoLocalizacion findById(Long id) {
//		
//		return encargadoDao.findById(id).orElse(null);
//	}
//
//	@Override
//	@Transactional
//	public EncargadoLocalizacion save(EncargadoLocalizacion encargado) {
//		
//		return encargadoDao.save(encargado);
//	}
//
//	@Override
//	@Transactional
//	public void delete(Long id) {
//		
//		encargadoDao.deleteById(id);
//
//	}

}
