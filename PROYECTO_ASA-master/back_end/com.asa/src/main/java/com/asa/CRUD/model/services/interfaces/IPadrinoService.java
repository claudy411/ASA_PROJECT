package com.asa.CRUD.model.services.interfaces;

import java.util.List;

import com.asa.CRUD.dto.PadrinoDto;
import com.asa.CRUD.model.entity.Padrino;

public interface IPadrinoService extends ICRUD<Padrino, Long> {
	
	public List<Padrino> getPadrinos(Long id);

}
