package com.estore.dao;

import java.util.List;

import com.estore.entity.security.Master;
import com.estore.entity.security.MasterRole;

public interface MasterDAO {
	Master findById(String id);
	List<Master> findAll();
	void create(Master entity);
	void update(Master entity);
	void delete(String id);
	List<MasterRole> findMasterRolesbyMasterID(String masterId);
}
