package com.asa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Adoptante;

public interface IAdoptanteDao extends JpaRepository<Adoptante, Long> {

}
