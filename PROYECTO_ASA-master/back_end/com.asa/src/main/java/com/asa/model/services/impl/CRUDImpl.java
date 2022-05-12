package com.asa.model.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asa.model.IGenericDao;

public abstract class CRUDImpl<T,ID> {

	protected abstract IGenericDao<T, ID> getRepo();
	
	public List<T> findAll() throws Exception{
		return getRepo().findAll(); 
	}
	
	public Page<T> findAll(Pageable pageable){
		return getRepo().findAll(pageable);
	}
	
	public T findById(ID id) throws Exception{
		return getRepo().findById(id).orElse(null);
	}
	
	public T save(T t) throws Exception{
		return getRepo().save(t);
	}
	
	public void delete(ID id) throws Exception{
		getRepo().deleteById(id);
	}
	public T modificar (T t) throws Exception{
		return getRepo().save(t);
	}
	
}
