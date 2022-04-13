package com.mc.main.ims.dbas;

import java.util.List;

public interface DatabaseAccessObject <T> {
	
	public boolean create(T model);
	public T read(Integer id);
	public List<T> readAll();
	public void update(Integer id, T replacer);
	public boolean delete(Integer id);
	
}
