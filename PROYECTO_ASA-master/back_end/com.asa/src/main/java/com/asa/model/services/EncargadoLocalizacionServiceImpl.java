package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IEncargadoLocalizacionDao;
import com.asa.model.entity.EncargadoLocalizacion;

@Service
public class EncargadoLocalizacionServiceImpl implements IService<EncargadoLocalizacion> {

	@Autowired
	private IEncargadoLocalizacionDao encargadoDao;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<EncargadoLocalizacion> findAll() {
		
		return encargadoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<EncargadoLocalizacion> findAll(Pageable pageable) {
		
		return encargadoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public EncargadoLocalizacion findById(Long id) {
		
		return encargadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EncargadoLocalizacion save(EncargadoLocalizacion encargado) {
		
		return encargadoDao.save(encargado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		encargadoDao.deleteById(id);

	}

}
