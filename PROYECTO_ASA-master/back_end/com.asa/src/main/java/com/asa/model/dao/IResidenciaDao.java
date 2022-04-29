package com.asa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Residencia;

public interface IResidenciaDao extends JpaRepository<Residencia, Long> {

}
