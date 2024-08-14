package com.estore.admin.controller;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("admin/revenue")
public class RevenueController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("byproduct")
	public String byProduct(ModelMap model) {
		String hql = "SELECT d.product.name," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY d.product.name";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Sản phẩm");
		
		return "admin/util/revenue";
	}
	
	@RequestMapping("bycategory")
	public String byCategory(ModelMap model) {
		String hql = "SELECT d.product.category.nameVN," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY d.product.category.nameVN";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Loại mặt hàng");
		
		return "admin/util/revenue";
	}
	
	@RequestMapping("bysupplier")
	public String bySupplier(ModelMap model) {
		String hql = "SELECT d.product.supplier.name," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY d.product.supplier.name";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Nhà cung cấp");
		
		return "admin/util/revenue";
	}
	
	@RequestMapping("bycustomer")
	public String byCustomer(ModelMap model) {
		String hql = "SELECT d.order.customer.id," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY d.order.customer.id";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Khách hàng");
		
		return "admin/util/revenue";
	}
	
	@RequestMapping("byyear")
	public String byYear(ModelMap model) {
		String hql = "SELECT YEAR(d.order.orderDate)," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY YEAR(d.order.orderDate)";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Năm");
		
		return "admin/util/revenue";
	}
	
	@RequestMapping("bymonth")
	public String byMonth(ModelMap model) {
		String hql = "SELECT MONTH(d.order.orderDate)," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY MONTH(d.order.orderDate)" +
				" ORDER BY MONTH(d.order.orderDate)";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Tháng");
		
		return "admin/util/revenue";
	}
	
	@RequestMapping("byquarter")
	public String byQuarter(ModelMap model) {
		String hql = "SELECT CAST(CEILING(MONTH(d.order.orderDate)/3.0) as int)," +
				" SUM(d.quantity)," +
				" SUM(d.quantity*d.unitPrice*(1-d.discount))," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetails d" +
				" GROUP BY CAST(CEILING(MONTH(d.order.orderDate)/3.0) as int)" +
				" ORDER BY CAST(CEILING(MONTH(d.order.orderDate)/3.0) as int)";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Quí");
		
		return "admin/util/revenue";
	}
}
