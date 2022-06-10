package com.asa.CRUD.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asa.CRUD.model.entity.Evento;
import com.asa.CRUD.model.entity.Localizacion;


public interface IEventoDao extends IGenericDao<Evento, Long> {
	
	@Query("FROM Localizacion where id=:id")
	List<Localizacion> findByLocalizacion(@Param("id") Long id);
	

	@Query(value="select foto from eventos where id=:id", nativeQuery = true)
	String getFoto(@Param("id") Long id);
}
