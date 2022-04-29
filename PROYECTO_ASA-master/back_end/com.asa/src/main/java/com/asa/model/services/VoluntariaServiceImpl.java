package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IVolutariaDao;
import com.asa.model.entity.Voluntaria;


@Service
public class VoluntariaServiceImpl implements IService<Voluntaria> {

	@Autowired
	private IVolutariaDao voluntariaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Voluntaria> findAll() {
		
		return voluntariaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Voluntaria> findAll(Pageable pageable) {
		
		return voluntariaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Voluntaria findById(Long id) {
	
		return voluntariaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Voluntaria save(Voluntaria voluntaria) {
		
		return voluntariaDao.save(voluntaria);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		voluntariaDao.deleteById(id);
	}

}
