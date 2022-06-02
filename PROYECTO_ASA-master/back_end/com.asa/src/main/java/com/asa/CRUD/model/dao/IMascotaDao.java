package com.asa.CRUD.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asa.CRUD.model.entity.Mascota;

public interface IMascotaDao extends IGenericDao<Mascota,Long>{
	
	@Query("FROM Mascota where tipo=:tipo")
	List<Mascota> findByTipo(@Param("tipo") String tipo);
	
	@Query("FROM Mascota where situacion=:situacion")
	List<Mascota> findBySituacion(@Param("situacion") String situacion);
	
	@Query(value="select foto_perfil from mascotas where id=:id", nativeQuery = true)
	String getFoto(@Param("id") Long id);
}
