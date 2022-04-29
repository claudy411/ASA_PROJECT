package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IAdoptanteDao;
import com.asa.model.entity.Adoptante;

@Service
public class AdoptanteServiceImpl implements IService<Adoptante> {

	@Autowired
	private IAdoptanteDao adoptanteDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Adoptante> findAll() {

		return adoptanteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Adoptante> findAll(Pageable pageable) {
		
		return adoptanteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Adoptante findById(Long id) {
		
		return adoptanteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Adoptante save(Adoptante adoptante) {
		
		return adoptanteDao.save(adoptante);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		adoptanteDao.deleteById(id);
	}

}
