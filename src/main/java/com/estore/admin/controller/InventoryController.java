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
@RequestMapping("/admin/inventory")
public class InventoryController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("bycategory")
	public String byCategory(ModelMap model) {
		String hql = "SELECT p.category.nameVN," +
				" SUM(p.quantity)," +
				" SUM(p.quantity*p.unitPrice)," +
				" MIN(p.unitPrice)," +
				" MAX(p.unitPrice)," +
				" AVG(p.unitPrice)" +
				" FROM Product p" +
				" GROUP BY p.category.nameVN";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Loại mặt hàng");
		
		return "admin/util/inventory";
	}
	
	@RequestMapping("bysupplier")
	public String bySupplier(ModelMap model) {
		String hql = "SELECT p.supplier.name," +
				" SUM(p.quantity)," +
				" SUM(p.quantity*p.unitPrice)," +
				" MIN(p.unitPrice)," +
				" MAX(p.unitPrice)," +
				" AVG(p.unitPrice)" +
				" FROM Product p" +
				" GROUP BY p.supplier.name";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("list", list);
		model.addAttribute("group", "Nhà cung cấp");
		
		return "admin/util/inventory";
	}
}
