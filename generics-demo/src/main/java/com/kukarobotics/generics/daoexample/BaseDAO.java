package com.kukarobotics.generics.daoexample;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T extends BusinessObject, ID extends Serializable> {

	void save(T enitity);

	void delete(T entity);

	T get(ID id);

	List<T> getAll();

	void update(T entity);
}
