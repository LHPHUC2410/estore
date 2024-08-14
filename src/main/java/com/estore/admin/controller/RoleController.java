package com.estore.admin.controller;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estore.entity.Product;
import com.estore.entity.security.ActionRole;
import com.estore.entity.security.MasterRole;
import com.estore.entity.security.Role;

@Transactional
@Controller
@RequestMapping("admin/role")
public class RoleController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext app;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Role role = new Role();
		model.addAttribute("model", role);
		model.addAttribute("list", getItems());
		return "admin/role/index";
	}
	
	@RequestMapping("insert")
	public String insert(ModelMap model, 
			@Valid @ModelAttribute("model") Role role, BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi");
			model.addAttribute("list", getItems());
			return "admin/role/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(session.find(Role.class, role.getId()) != null) {
			model.addAttribute("message", "Mã vai trò này đã tồn tại");
			model.addAttribute("list", getItems());
			return "admin/role/index";
		}
		try {
			session.save(role);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công");
			model.addAttribute("model", new Role());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại");
		}
		finally{
			session.close();
		}
		model.addAttribute("status", 1);
		model.addAttribute("list", getItems());
		return "admin/role/index";
	}
	
	@RequestMapping("update")
	public String update(ModelMap model, 
			@Valid @ModelAttribute("model") Role role, BindingResult errors) {
		model.addAttribute("status", 2);
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi");
			model.addAttribute("list", getItems());
			return "admin/role/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(role);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/role/index";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, 
			@ModelAttribute("model") Role role) {
		if(actionsRoles(role.getId()).size() > 0) {
			model.addAttribute("message", "Không thể xóa vì đã phân vai trò hành động");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new Role());
			return "admin/role/index";
		} else if (masterRoles(role.getId()).size() > 0) {
			model.addAttribute("message", "Không thể xóa vì đã phân vai trò chính");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new Role());
			return "admin/role/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(role);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
			model.addAttribute("model", new Role());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/role/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(ModelMap model, 
			@PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Role role = (Role) session.get(Role.class, id);
		model.addAttribute("status", 2);
		model.addAttribute("model", role);
		model.addAttribute("list", getItems());
		return "admin/role/index";
	}
	
	public List<Role> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Role";
		Query query = session.createQuery(hql);
		List<Role> list = query.list();
		return list;
	}
	public List<ActionRole> actionsRoles(String roleId){
		String hql="FROM ActionRole ar WHERE ar.role.id=:rid";
		Session session = factory.getCurrentSession();
		TypedQuery<ActionRole> query = session.createQuery(hql,ActionRole.class);
		query.setParameter("rid", roleId);
		List<ActionRole> list = query.getResultList();
		return list;
	}
	public List<MasterRole> masterRoles(String roleId){
		String hql="FROM MasterRole ar WHERE ar.role.id=:rid";
		Session session = factory.getCurrentSession();
		TypedQuery<MasterRole> query = session.createQuery(hql,MasterRole.class);
		query.setParameter("rid", roleId);
		List<MasterRole> list = query.getResultList();
		return list;
	}
}