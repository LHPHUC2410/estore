package com.estore.admin.controller;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.dao.OrderDetailsDAO;
import com.estore.entity.Order;
import com.estore.entity.OrderDetails;
import com.estore.entity.Product;

@Transactional
@Controller("adminOrderController")
@RequestMapping("admin/order")
public class OrderController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("index")
	public String index(ModelMap model) {
//		Order order = new Order();
//	    model.addAttribute("model", order);
//		model.addAttribute("list", getItems());
		return "admin/order/index";
	}

//	@RequestMapping("insert")
//	public String insert(ModelMap model, 
//			@ModelAttribute("model") Order order) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		try {
//			session.save(order);
//			t.commit();
//			model.addAttribute("message", "Them moi thanh cong");
//			model.addAttribute("model", new Order());
//		} 
//		catch (Exception e) {
//			t.rollback();
//			model.addAttribute("message", "Them moi that bai");
//		}
//		finally{
//			session.close();
//		}
//		model.addAttribute("list", getItems());
//		return "admin/order/index";
//	}
//	
//	@RequestMapping("update")
//	public String update(ModelMap model, 
//			@ModelAttribute("model") Order order) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		try {
//			session.update(order);
//			t.commit();
//			model.addAttribute("message", "Cap nhat thanh cong");
//		} 
//		catch (Exception e) {
//			t.rollback();
//			model.addAttribute("message", "Cap nhat that bai");
//		}
//		finally{
//			session.close();
//		}
//		model.addAttribute("list", getItems());
//		return "admin/order/index";
//	}
//	
//	@RequestMapping("delete")
//	public String delete(ModelMap model, 
//			@ModelAttribute("model") Order order) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		try {
//			session.delete(order);
//			t.commit();
//			model.addAttribute("message", "Xoa thanh cong");
//			model.addAttribute("model", new Order());
//		} 
//		catch (Exception e) {
//			t.rollback();
//			model.addAttribute("message", "Xoa that bai");
//		}
//		finally{
//			session.close();
//		}
//		model.addAttribute("list", getItems());
//		return "admin/order/index";
//	}
//
//	@RequestMapping("edit/{id}")
//	public String edit(ModelMap model, 
//			@PathVariable("id") Integer id) {
//		Session session = factory.getCurrentSession();
//		Order order = (Order) session.get(Order.class, id);
//		model.addAttribute("model", order);
//		model.addAttribute("list", getItems());
//		return "admin/order/index";
//	}
	public List<Order> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order";
		Query query = session.createQuery(hql);
		List<Order> list = query.list();
		return list;
	}

//	@RequestMapping("detail/{id}")
//	public String detail(ModelMap model, @PathVariable("id") Integer id) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM OrderDetails od WHERE od.order.id = :oid";
//		TypedQuery<OrderDetails> query = session.createQuery(hql, OrderDetails.class);
//		query.setParameter("oid", id);
//		List<OrderDetails> list = query.getResultList();
//		model.addAttribute("details", list);
//		return "admin/order/index";
//	}



	// ====================
	@RequestMapping("load")
	public String loadPage(ModelMap model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order";
		Query query = session.createQuery(hql);
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);
		List<Order> list = query.list();

		model.addAttribute("list", list);
		return "nonlayout/admin/order/table";
	}

	@ResponseBody
	@RequestMapping("count")
	public String getPageCount(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(o) FROM Order o";
		Query query = session.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		double pages = Math.ceil((double) count / pageSize);
		return String.valueOf(pages);
	}
}
