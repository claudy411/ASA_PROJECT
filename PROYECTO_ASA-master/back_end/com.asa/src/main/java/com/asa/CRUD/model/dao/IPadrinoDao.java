package com.asa.CRUD.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asa.CRUD.dto.PadrinoDto;
import com.asa.CRUD.model.entity.Padrino;

public interface IPadrinoDao extends IGenericDao<Padrino, Long> {

	@Query("from Padrino where id_mascota=:id")
	List<Padrino> findByMascota(@Param("id") Long id);
}
