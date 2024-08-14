package com.estore.dao;

import java.util.List;

import com.estore.entity.Supplier;

public interface SupplierDAO {
	Supplier findById(String id);
	List<Supplier> findAllSup();
	Supplier create(Supplier entity);
	void update(Supplier entity);
	Supplier delete(Integer id);
	List<Supplier> getRandoms();
}
