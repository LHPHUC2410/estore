package com.estore.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.entity.Product;
import com.estore.entity.security.ActionRole;
import com.estore.entity.security.Role;
import com.estore.entity.security.WebAction;

@Transactional
@Controller
@RequestMapping("admin/webaction")
public class WebActionController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext app;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		WebAction webAction = new WebAction();
		model.addAttribute("model", webAction);
		model.addAttribute("list", getItems());
		return "admin/webaction/index";
	}
	
	@RequestMapping("insert")
	public String insert(ModelMap model, 
			@Validated @ModelAttribute("model") WebAction webAction, BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi");
			model.addAttribute("list", getItems());
			return "admin/webaction/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(session.find(WebAction.class, webAction.getId()) != null) {
			model.addAttribute("message", "Mã chức năng này đã tồn tại");
			model.addAttribute("list", getItems());
			return "admin/webaction/index";
		}
		try {
			session.save(webAction);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công");
			model.addAttribute("model", new WebAction());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/webaction/index";
	}
	
	@RequestMapping("update")
	public String update(ModelMap model, 
			@Valid @ModelAttribute("model") WebAction webAction, BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi");
			model.addAttribute("list", getItems());
			return "admin/webaction/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(webAction);
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
		return "admin/webaction/index";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, 
			@ModelAttribute("model") WebAction webAction, @PathVariable("id") Integer id) {
		
		List<ActionRole> listActionRoles = getActionsRoles(webAction.getId());
		if(listActionRoles.size() >0) {
			model.addAttribute("message", "Không thể xóa vì đã được phân quyền");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new WebAction());
			return "admin/webaction/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(webAction);
			t.commit();
			model.addAttribute("message", "Xoa thanh cong");
			model.addAttribute("model", new WebAction());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xoa that bai");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/webaction/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id) {
	    Session session = factory.getCurrentSession();
	    WebAction webAction = (WebAction) session.get(WebAction.class, id);
	    model.addAttribute("model", webAction);
	    model.addAttribute("list", getItems());
	    return "admin/webaction/index";
	}
	
	public List<WebAction> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM WebAction";
		Query query = session.createQuery(hql);
		List<WebAction> list = query.list();
		return list;
	}
	public List<ActionRole> getActionsRoles(Integer webActionId){
		String hql="FROM ActionRole ar WHERE ar.webAction.id=:wid";
		Session session = factory.getCurrentSession();
		TypedQuery<ActionRole> query = session.createQuery(hql,ActionRole.class);
		query.setParameter("wid", webActionId);
		List<ActionRole> list = query.getResultList();
		return list;
	}
	@RequestMapping("load")
	public String loadPage(ModelMap model,
	        @RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
	        @RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
	    Session session = factory.getCurrentSession();
	    String hql = "FROM WebAction";
	    Query query = session.createQuery(hql);
	    query.setFirstResult(pageNo * pageSize);
	    query.setMaxResults(pageSize);
	    List<WebAction> list = query.list();

	    model.addAttribute("list", list);
	    return "nonlayout/admin/webaction/table";
	}

	@ResponseBody
	@RequestMapping("count")
	public String getPageCount(@RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
	    Session session = factory.getCurrentSession();
	    String hql = "SELECT COUNT(w) FROM WebAction w";
	    Query query = session.createQuery(hql);
	    Long count = (Long) query.uniqueResult();
	    double pages = Math.ceil((double) count / pageSize);
	    return String.valueOf(pages);
	}
}
