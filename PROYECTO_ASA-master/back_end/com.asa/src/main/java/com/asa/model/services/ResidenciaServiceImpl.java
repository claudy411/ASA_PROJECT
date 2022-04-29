package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IResidenciaDao;
import com.asa.model.entity.Residencia;

@Service
public class ResidenciaServiceImpl implements IService<Residencia> {
	
	@Autowired
	private IResidenciaDao residenciaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Residencia> findAll() {
		
		return (List<Residencia>)residenciaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Residencia> findAll(Pageable paginador) {
		
		return residenciaDao.findAll(paginador);
	}

	@Override
	@Transactional(readOnly=true)
	public Residencia findById(Long id) {
	
		return residenciaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Residencia save(Residencia residencia) {
		
		return residenciaDao.save(residencia);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		residenciaDao.deleteById(id);
		
	}

}
