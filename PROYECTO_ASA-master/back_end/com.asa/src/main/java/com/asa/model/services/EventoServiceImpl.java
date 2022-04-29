package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IEventoDao;
import com.asa.model.entity.Evento;

@Service
public class EventoServiceImpl implements IService<Evento> {
	
	@Autowired
	private IEventoDao eventoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Evento> findAll() {
		
		return (List<Evento>) eventoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Evento> findAll(Pageable pageable) {
		
		return eventoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Evento findById(Long id) {
		
		return eventoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Evento save(Evento evento) {
		
		return eventoDao.save(evento);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		eventoDao.deleteById(id);
	}

}
