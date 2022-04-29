package com.asa.model.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.ImagenPerro;
import com.asa.model.entity.Perro;

public interface IImagenPerroDao extends JpaRepository<ImagenPerro, Long > {

	
	 
	 public List<ImagenPerro> findByPerroId(Long id);
}
