package com.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estore.entity.security.Master;
import com.estore.entity.security.MasterRole;

@Transactional
@Repository
public class MasterDAOImpl implements MasterDAO{

	@Autowired
	SessionFactory factory;

	@Override
	public Master findById(String id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Master entity = session.find(Master.class, id);
		return entity;
	}

	@Override
	public List<Master> findAll() {
		// TODO Auto-generated method stub
		String hql="FROM Masters";
		Session session = factory.getCurrentSession();
		TypedQuery<Master> query = session.createQuery(hql,Master.class);
		List<Master> list = query.getResultList();
		return list;
	}

	@Override
	public void create(Master entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.save(entity);
	}

	@Override
	public void update(Master entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Master entity = session.find(Master.class, id);
		session.delete(entity);
	}
	
	@Override
	public List<MasterRole> findMasterRolesbyMasterID(String masterId) 
	{
		String hql = "FROM MasterRole m WHERE m.master.id=:mId";
		Session session = factory.getCurrentSession();
		TypedQuery<MasterRole> query = session.createQuery(hql,MasterRole.class);
		query.setParameter("mId", masterId);
		List<MasterRole> list = query.getResultList();
		return list;
	}
}
