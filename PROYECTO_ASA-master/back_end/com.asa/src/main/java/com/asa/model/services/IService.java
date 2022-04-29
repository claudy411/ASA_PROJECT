package com.asa.model.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface IService<T> {

	public List<T> findAll();

	public Page<T> findAll(Pageable pageable);

	public T findById(Long id);

	public T save(T t);

	public void delete(Long id);
}
