package com.asa.CRUD.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asa.CRUD.model.entity.EncargadoLocalizacion;

public interface IEncargadoLocalizacionDao extends IGenericDao<EncargadoLocalizacion, Long> {

	@Query("FROM EncargadoLocalizacion where id_localizacion=:id")
	List<EncargadoLocalizacion> findByLocalizacion(@Param("id") Long id);
}
