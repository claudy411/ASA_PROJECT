package com.asa.model.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Perro;
import com.asa.model.entity.Residencia;




public interface IPerroDao extends JpaRepository<Perro, Long> {	
	

}
