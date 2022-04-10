package com.common.model;

import java.util.List;

public interface JDBC_DAO_Interface<T> {
	public T insert(T t);
	public void delete();
	public T update(T t);
	public T getOneById(Integer id);
	public List<T> getAll();
}
