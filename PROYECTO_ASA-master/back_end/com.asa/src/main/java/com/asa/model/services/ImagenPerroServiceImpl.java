package com.asa.model.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IImagenPerroDao;
import com.asa.model.entity.ImagenPerro;
import com.asa.model.entity.Perro;


@Service
public class ImagenPerroServiceImpl implements IImagenPerroService {
	
	@Autowired
	private IImagenPerroDao img_dao;

	@Override
	@Transactional
	public ImagenPerro incluirImagen(ImagenPerro img) {
		
		return img_dao.save(img);
	}

	@Override
	@Transactional
	public ImagenPerro verImagenById(Long id) {
		
		return img_dao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public List<ImagenPerro> verImagenByPerroId(Long id) {
		
		return img_dao.findByPerroId(id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		img_dao.deleteById(id);

	}

}
