package com.asa.CRUD.model.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ICRUD<T,ID> {

	public List<T> findAll() throws Exception;

//	public Page<T> findAll(Pageable pageable);

	public T findById(ID id) throws Exception;

	public T save(T t) throws Exception;

	public void delete(ID id) throws Exception;
	
	public T modificar (T t) throws Exception;
}
