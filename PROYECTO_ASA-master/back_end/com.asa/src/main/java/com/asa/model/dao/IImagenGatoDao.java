package com.asa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.ImagenGato;


public interface IImagenGatoDao extends JpaRepository<ImagenGato, Long > {

//	public List<ImagenGato> findByGatoId(Long id);

}
