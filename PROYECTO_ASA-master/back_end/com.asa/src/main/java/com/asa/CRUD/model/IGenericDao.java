package com.asa.CRUD.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericDao <T,ID> extends JpaRepository<T, ID>{

}
