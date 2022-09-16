package com.vortexbird.facturacion.entity.service;

import java.util.List;
import java.util.Optional;

import com.vortexbird.facturacion.exception.VortexbirdException;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 */
public interface GenericService<T, ID> {

	public List<T> findAll();

	public Optional<T> findById(ID id);

	public T save(T entity) throws VortexbirdException;

	public T update(T entity) throws VortexbirdException;

	public void delete(T entity) throws VortexbirdException;

	public void deleteById(ID id) throws VortexbirdException;

	public void validate(T entity) throws VortexbirdException;

	public Long count();

}
