package com.estore.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estore.entity.Supplier;
import com.estore.entity.Product;

@Transactional
@Repository
public class SupplierDAOImpl implements SupplierDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public List<Supplier> findAllSup() {
		String hql = "FROM Supplier";
		Session session = factory.getCurrentSession();
		TypedQuery<Supplier> query = session.createQuery(hql, Supplier.class);
		List<Supplier> listS = query.getResultList();
		return listS;
	}

	@Override
	public Supplier create(Supplier entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Supplier entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Supplier delete(Integer id) {
		Session session = factory.getCurrentSession();
		Supplier entity = session.find(Supplier.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<Supplier> getRandoms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier findById(String id) {
		Session session = factory.getCurrentSession();
		Supplier entity = session.find(Supplier.class, id);
		return entity;
	}

	/*
	 * @Override public List<Supplier> getRandoms() { String
	 * hql="FROM Supplier c WHERE size(c.products) >= 0"; Session session =
	 * factory.getCurrentSession(); TypedQuery<Supplier> query =
	 * session.createQuery(hql,Supplier.class); List<Supplier> list =
	 * query.getResultList(); Collections.shuffle(list); list = list.subList(0, 1);
	 * list.forEach(c->{ List<Product> prods = c.getProducts();
	 * Collections.shuffle(prods); c.setProducts(prods.subList(0, 1)); }); return
	 * list; }
	 */

}
