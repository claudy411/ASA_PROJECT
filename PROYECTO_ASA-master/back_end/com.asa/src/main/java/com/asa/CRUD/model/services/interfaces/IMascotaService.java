package com.asa.CRUD.model.services.interfaces;

import java.util.List;

import com.asa.CRUD.model.entity.Mascota;
import com.asa.CRUD.model.entity.Residencia;

public interface IMascotaService extends ICRUD<Mascota, Long> {
	
	public List<Mascota> buscarPorTipo(String tipo);
	
	public List<Mascota> buscarPorSituacion(String situacion);
	
	public String verFoto(Long id);

	public List<Residencia> buscarPorResidencia(Long id);
}
