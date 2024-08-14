package com.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estore.entity.Order;
import com.estore.entity.OrderDetails;
import com.estore.entity.Product;

@Transactional
@Repository
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
	
	@Autowired
	SessionFactory factory;
	
	
	@Override
	public OrderDetails findById(Integer id) {
		Session session = factory.getCurrentSession();
		OrderDetails entity = session.find(OrderDetails.class, id);
		return entity;
	}

	@Override
	public List<OrderDetails> findAll() {
		String hql="FROM OrderDetails";
		Session session = factory.getCurrentSession();
		TypedQuery<OrderDetails> query = session.createQuery(hql,OrderDetails.class);
		List<OrderDetails> list = query.getResultList();
		return list;
	}

	@Override
	public OrderDetails create(OrderDetails entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(OrderDetails entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public OrderDetails delete(Integer id) {
		Session session = factory.getCurrentSession();
		OrderDetails entity = session.find(OrderDetails.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<OrderDetails> findByOrder(Order order) {
		String hql="FROM OrderDetails d WHERE d.order.id =:oid";
		Session session = factory.getCurrentSession();
		TypedQuery<OrderDetails> query = session.createQuery(hql,OrderDetails.class);
		query.setParameter("oid", order.getId());
		List<OrderDetails> list = query.getResultList();
		return list;
	}

	@Override
	public List<OrderDetails> findByProduct(Product product) {
		String hql="FROM OrderDetails d WHERE d.product.id =:pid";
		Session session = factory.getCurrentSession();
		TypedQuery<OrderDetails> query = session.createQuery(hql,OrderDetails.class);
		query.setParameter("pid", product.getId());
		List<OrderDetails> list = query.getResultList();
		return list;
	}

}
