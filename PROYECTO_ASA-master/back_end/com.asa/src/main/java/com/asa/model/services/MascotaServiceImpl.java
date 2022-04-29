package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IMascotaDao;
import com.asa.model.entity.Mascota;

@Service
public class MascotaServiceImpl implements IService<Mascota> {

	@Autowired
	
	private IMascotaDao mascotaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Mascota> findAll() {
		
		return mascotaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Mascota> findAll(Pageable paginador) {

		return mascotaDao.findAll(paginador);
	}

	@Override
	@Transactional(readOnly=true)
	public Mascota findById(Long id) {
		
		return mascotaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Mascota save(Mascota mascota) {
		
		return mascotaDao.save(mascota);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		mascotaDao.deleteById(id);
	}

}
