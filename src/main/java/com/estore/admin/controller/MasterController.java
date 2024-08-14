package com.estore.admin.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estore.dao.MasterDAO;
import com.estore.entity.security.Master;

@Transactional
@Controller
@RequestMapping("admin/master")
public class MasterController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	MasterDAO mdao;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Master master = new Master();
		model.addAttribute("model", master);
		model.addAttribute("list", getItems());
		return "admin/master/index";
	}
	
	@RequestMapping("insert")
	public String insert(ModelMap model, 
			@Validated @ModelAttribute("model") Master master, BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(mdao.findById(master.getId()) != null) 
		{
			model.addAttribute("message", "Tên đăng nhập đã được sử dụng");
			model.addAttribute("list", getItems());
			return "admin/master/index";
		}
		
		try {
			session.save(master);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công");
			model.addAttribute("model", new Master());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới không thành công");
		}
		finally{
			session.close();
		}
		model.addAttribute("status", 1);
		model.addAttribute("list", getItems());
		return "admin/master/index";
	}
	
	@RequestMapping("update")
	public String update(ModelMap model, 
			@Validated @ModelAttribute("model") Master master, BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		model.addAttribute("status", 2);
		try {
			session.update(master);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật không thành công");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/master/index";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, 
			@ModelAttribute("model") Master master) {
		if(mdao.findMasterRolesbyMasterID(master.getId()).size() > 0) 
		{
			model.addAttribute("message", "Không thể xóa vì tài khoản đã đc phân quyền");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new Master());
			return "admin/master/index";
			//System.out.println("Khoong the xoa ");
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(master);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
			model.addAttribute("model", new Master());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa không thành công");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/master/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(ModelMap model, 
			@PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Master master = (Master) session.get(Master.class, id);
		model.addAttribute("status", 2);
		model.addAttribute("model", master);
		model.addAttribute("list", getItems());
		return "admin/master/index";
	}
	
	public List<Master> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Master";
		Query query = session.createQuery(hql);
		List<Master> list = query.list();
		return list;
	}
}
