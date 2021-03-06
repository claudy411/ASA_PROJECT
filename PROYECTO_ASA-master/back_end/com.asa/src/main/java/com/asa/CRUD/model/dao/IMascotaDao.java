package com.asa.CRUD.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asa.CRUD.model.entity.Mascota;
import com.asa.CRUD.model.entity.Residencia;

public interface IMascotaDao extends IGenericDao<Mascota,Long>{
	
	@Query("FROM Mascota where tipo=:tipo")
	List<Mascota> findByTipo(@Param("tipo") String tipo);
	
	@Query("FROM Mascota where situacion=:situacion")
	List<Mascota> findBySituacion(@Param("situacion") String situacion);
	
	@Query(value="select foto_perfil from mascotas where id=:id", nativeQuery = true)
	String getFoto(@Param("id") Long id);
	
	@Query("FROM Residencia where id=:id")
	List<Residencia> findByResidencia(@Param("id") Long id);
	
	@Query("from Mascota where (situacion='acogida' or situacion='residencia') and tipo=:tipo")
	List<Mascota> findByTipoAndSituacion(@Param("tipo") String tipo);
}
