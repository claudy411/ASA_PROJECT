package com.asa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Gato;


public interface IGatoDao extends JpaRepository<Gato, Long>{

}
