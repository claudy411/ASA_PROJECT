package com.asa.model.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asa.model.dao.IAcogidaDao;
import com.asa.model.entity.Acogida;
@Service
public class AcogidaServiceImpl implements IService<Acogida> {

	private IAcogidaDao acogidaDao;
	
	@Override
	public List<Acogida> findAll() {
		// TODO Auto-generated method stub
		return acogidaDao.findAll();
	}

	@Override
	public Page<Acogida> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return acogidaDao.findAll(pageable);
	}

	@Override
	public Acogida findById(Long id) {
		// TODO Auto-generated method stub
		return acogidaDao.findById(id).orElse(null);
	}

	@Override
	public Acogida save(Acogida t) {
		// TODO Auto-generated method stub
		return acogidaDao.save(t);
	}

	@Override
	public void delete(Long id) {
		
		acogidaDao.deleteById(id);
		
	}

}
