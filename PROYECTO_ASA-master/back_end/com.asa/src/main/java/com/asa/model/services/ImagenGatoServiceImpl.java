package com.asa.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asa.model.dao.IImagenGatoDao;

import com.asa.model.entity.ImagenGato;


@Service
public class ImagenGatoServiceImpl implements IImagenGatoService {

	@Autowired
	private IImagenGatoDao img_dao;
	
	
	@Override
	@Transactional
	public ImagenGato incluirImagen(ImagenGato img) {
		return img_dao.save(img);
	}

	@Override
	@Transactional(readOnly=true)
	public ImagenGato verImagenById(Long id) {
		
		return img_dao.findById(id).orElse(null);
	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public List<ImagenGato> verImagenByGatoId(Long id) {
//
//		return img_dao.findByGatoId(id);
//	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		img_dao.deleteById(id);

	}

}
