package com.asa.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asa.model.IGenericDao;
import com.asa.model.IImagenMascotaDao;
import com.asa.model.entity.ImagenMascota;
import com.asa.model.services.IImagenMascotaService;

@Service
public class ImagenMascotaServiceImpl extends CRUDImpl<ImagenMascota, Long> implements IImagenMascotaService {

	@Autowired
	private IImagenMascotaDao imgDao;
	
	@Override
	protected IGenericDao<ImagenMascota, Long> getRepo() {
		// TODO Auto-generated method stub
		return imgDao;
	}

	
}
