package com.asa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Localizacion;

public interface ILocalizacionDao extends JpaRepository<Localizacion, Long> {

}
