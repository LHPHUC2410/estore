package com.estore.admin.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.estore.dao.ProductDAO;
import com.estore.dao.SupplierDAO;
import com.estore.entity.Supplier;

@Transactional
@Controller
@RequestMapping("admin/supplier")
public class SupplierController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	SupplierDAO sdao;
	
	@Autowired
	ProductDAO pdao;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Supplier supplier = new Supplier();
		model.addAttribute("model", supplier);
		model.addAttribute("list", getItems());
		return "admin/supplier/index";
	}
	
	@RequestMapping("insert")
	public String insert(ModelMap model, 
			@Validated @ModelAttribute("model") Supplier supplier,
			BindingResult errors,
			@RequestParam("upLogo") MultipartFile file) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(sdao.findById(supplier.getId()) != null) 
		{
			model.addAttribute("message", "ID đã tồn tại");
			model.addAttribute("list", getItems());
			return "admin/supplier/index";
		}
		try {
			if(file.isEmpty()){
				supplier.setLogo("logo.png");
			}
			else{
				supplier.setLogo(System.currentTimeMillis()+file.getOriginalFilename());
				String path = app.getRealPath("static/images/suppliers/"+supplier.getLogo());
				file.transferTo(new File(path));
			}
			session.save(supplier);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công");
			model.addAttribute("model", new Supplier());
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
		return "admin/supplier/index";
	}
	
	@RequestMapping("update")
	public String update(ModelMap model, 
			@Validated @ModelAttribute("model") Supplier supplier,
			BindingResult errors,
			@RequestParam("upLogo") MultipartFile file) {
		model.addAttribute("status", 2);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if(!file.isEmpty()){
				supplier.setLogo(System.currentTimeMillis()+file.getOriginalFilename());
				String path = app.getRealPath("static/images/suppliers/"+supplier.getLogo());
				file.transferTo(new File(path));
			}
			session.update(supplier);
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
		return "admin/supplier/index";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, 
			@ModelAttribute("model") Supplier supplier) {
		
		if(pdao.findBySupplierId(supplier.getId()).size() > 0) {
			model.addAttribute("message", "Không thể xóa vì nhà cung cấp đã cung cấp sản phẩm");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new Supplier());
			return "admin/supplier/index";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(supplier);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
			model.addAttribute("model", new Supplier());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/supplier/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(ModelMap model, 
			@PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Supplier supplier = (Supplier) session.get(Supplier.class, id);
		model.addAttribute("status", 2);
		model.addAttribute("model", supplier);
		model.addAttribute("list", getItems());
		return "admin/supplier/index";
	}
	
	public List<Supplier> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Supplier";
		Query query = session.createQuery(hql);
		List<Supplier> list = query.list();
		return list;
	}
}
