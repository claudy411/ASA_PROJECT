package com.asa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.model.entity.Mascota;

public interface IMascotaDao extends JpaRepository<Mascota,Long>{

}
