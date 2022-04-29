package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IPerroDao;
import com.asa.model.dao.IResidenciaDao;
import com.asa.model.entity.Perro;
import com.asa.model.entity.Residencia;

@Service
public class PerroServiceImpl implements IService<Perro> {
	
	@Autowired
	private IPerroDao perro;
	private IResidenciaDao resi;

	@Override
	@Transactional(readOnly=true)
	public List<Perro> findAll() {
		
		return (List<Perro>) perro.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Perro> findAll(Pageable paginador) {
		
		return perro.findAll(paginador);
	}

	@Override
	@Transactional(readOnly=true)
	public Perro findById(Long id) {
		
		return perro.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Perro save(Perro nuevo) {
		
		return perro.save(nuevo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		perro.deleteById(id);

	}

	
	@Transactional(readOnly = true)
	public List<Residencia> findAllResidencias() {
		
		return resi.findAll();
	}



}