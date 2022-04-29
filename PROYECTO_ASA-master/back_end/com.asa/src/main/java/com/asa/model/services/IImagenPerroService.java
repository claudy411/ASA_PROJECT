package com.asa.model.services;

import java.util.List;
import java.util.Set;

import com.asa.model.entity.ImagenPerro;
import com.asa.model.entity.Perro;

public interface IImagenPerroService {
	

	public ImagenPerro incluirImagen(ImagenPerro img);
	
	public ImagenPerro verImagenById(Long id);
	
	public List<ImagenPerro> verImagenByPerroId(Long id);
	
	public void delete(Long id);
}
