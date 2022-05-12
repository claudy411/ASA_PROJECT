package com.asa.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericDao <T,ID> extends JpaRepository<T, ID>{

}
