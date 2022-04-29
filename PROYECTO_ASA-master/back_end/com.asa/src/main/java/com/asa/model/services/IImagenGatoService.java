package com.asa.model.services;

import java.util.List;

import com.asa.model.entity.ImagenGato;


public interface IImagenGatoService {
	
	public ImagenGato incluirImagen(ImagenGato img);
	
	public ImagenGato verImagenById(Long id);
	
//	public List<ImagenGato> verImagenByGatoId(Long id);
	
	public void delete(Long id);

	

}
