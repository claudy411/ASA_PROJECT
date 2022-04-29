package com.asa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Evento;

public interface IEventoDao extends JpaRepository<Evento, Long> {

}
