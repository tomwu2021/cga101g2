package com.common.model;

import java.util.List;

public interface JDBCDAO_Interface<T> {
	public T insert(T t);
	public boolean delete(T t);
	public T update(T t);
	public T getOneById(Integer id);
	public List<T> getAll();
}
