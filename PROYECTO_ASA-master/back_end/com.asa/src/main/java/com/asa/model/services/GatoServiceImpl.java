package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IGatoDao;
import com.asa.model.entity.Gato;

@Service
public class GatoServiceImpl implements IService<Gato>{
	
	@Autowired
	private IGatoDao gato;

	@Override
	@Transactional(readOnly=true)
	public List<Gato> findAll() {
		
		return (List<Gato>)gato.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Gato> findAll(Pageable paginador) {
		
		return gato.findAll(paginador);
	}

	@Override
	@Transactional(readOnly=true)
	public Gato findById(Long id) {
		
		return gato.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Gato save(Gato nuevo) {
		
		return gato.save(nuevo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		gato.deleteById(id);

	}

}
