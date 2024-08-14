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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estore.dao.OrderDetailsDAO;
import com.estore.dao.ProductDAO;
import com.estore.entity.Category;
import com.estore.entity.Product;
import com.estore.entity.Supplier;

@Transactional
@Controller("adminProductController")
@RequestMapping("admin/product")
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	OrderDetailsDAO oddao;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Product product = new Product();
		//model.addAttribute("list", getItems());

		model.addAttribute("model", product);
		return "admin/product/index";
	}
	
	@RequestMapping("insert")
	public String insert(ModelMap model, 
			@Validated @ModelAttribute("model") Product product,
			BindingResult errors, @RequestParam("photo_file") MultipartFile file) {
		if (errors.hasErrors()) 
		{
			model.addAttribute("message", "Vui lòng sửa lỗi");
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if(file.isEmpty()){
				product.setImage("logo.png");
			}
			else{
				product.setImage(System.currentTimeMillis()+file.getOriginalFilename());
				String path = app.getRealPath("/images/products/"+product.getImage());
				file.transferTo(new File(path));
			}
			session.save(product);
			t.commit();
			model.addAttribute("message", "Thêm mới sản phẩm thành công");
			model.addAttribute("model", new Product());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/product/index";
	}
	
	@RequestMapping("update")
	public String update(ModelMap model, 
			@Validated @ModelAttribute("model") Product product,
			BindingResult errors,
			@RequestParam("photo_file") MultipartFile file) {
		if (errors.hasErrors()) 
		{
			model.addAttribute("message", "Vui lòng sửa lỗi");
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if(!file.isEmpty()){
				product.setImage(System.currentTimeMillis()+file.getOriginalFilename());
				String path = app.getRealPath("/static/images/products/"+product.getImage());
				file.transferTo(new File(path));
			}
			session.update(product);
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
		return "admin/product/index";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,
			@ModelAttribute("model") Product product,
			@PathVariable("id") Integer id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		if(oddao.findByProduct(product).size() > 0) {
			model.addAttribute("message", "Không thể xóa vì sản phẩm đã tạo đơn hàng");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new Product());
			return "admin/product/index";
		}

		try {
			session.delete(product);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
			model.addAttribute("model", new Product());
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa không thành công");
		}
		finally{
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/product/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(ModelMap model, 
			@PathVariable("id") Integer id) {
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		model.addAttribute("model", product);
		model.addAttribute("list", getItems());
		return "admin/product/index";
	}
	
	public List<Product> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		return list;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCates() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
	
	@ModelAttribute("suppliers")
	public List<Supplier> getSuppliers() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Supplier";
		Query query = session.createQuery(hql);
		List<Supplier> list = query.list();
		return list;
	}
	
	//====================
	@RequestMapping("load")
	public String loadPage(ModelMap model,
	        @RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
	        @RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
	    Session session = factory.getCurrentSession();
	    String hql = "FROM Product";
	    Query query = session.createQuery(hql);
	    query.setFirstResult(pageNo * pageSize);
	    query.setMaxResults(pageSize);
	    List<Product> list = query.list();

	    model.addAttribute("list", list);
	    return "nonlayout/admin/product/table";
	}

	@ResponseBody
	@RequestMapping("count")
	public String getPageCount(@RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
	    Session session = factory.getCurrentSession();
	    String hql = "SELECT COUNT(p) FROM Product p";
	    Query query = session.createQuery(hql);
	    Long count = (Long) query.uniqueResult();
	    double pages = Math.ceil((double) count / pageSize);
	    return String.valueOf(pages);
	}

}
