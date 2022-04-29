package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.ILocalizacionDao;
import com.asa.model.entity.Localizacion;

@Service
public class LocalizacionServiceImpl implements IService<Localizacion> {
	
	@Autowired
	private ILocalizacionDao locDao;

	@Override
	@Transactional(readOnly=true)
	public List<Localizacion> findAll() {
		
		return locDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Localizacion> findAll(Pageable pageable) {
		
		return locDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Localizacion findById(Long id) {
		
		return locDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Localizacion save(Localizacion localizacion) {
		
		return locDao.save(localizacion);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		locDao.deleteById(id);

	}

}
