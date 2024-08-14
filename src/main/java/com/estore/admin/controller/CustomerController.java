package com.estore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.CustomerDAO;
import com.estore.dao.OrderDAO;
import com.estore.entity.Customer;
import com.estore.entity.Product;

@Transactional
@Controller
@RequestMapping("admin/customer/")
public class CustomerController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext app;
	@Autowired
	OrderDAO odao;

	@Autowired
	CustomerDAO cdao;

	@RequestMapping("index")
	public String index(ModelMap model) {
		Customer customer = new Customer();
		model.addAttribute("model", customer);
		//model.addAttribute("list", getItems());
		return "admin/customer/index";
	}

	@RequestMapping("insert")
	public String insert(ModelMap model, @RequestParam("photo_file") MultipartFile file,
			@Valid @ModelAttribute("model") Customer customer, BindingResult errors) {
		if (cdao.findById(customer.getId()) != null) {
			model.addAttribute("message", "Mã khách hàng này đã tồn tại");
			model.addAttribute("list", getItems());
			System.out.println("Customer already exists: " + customer);

			return "admin/customer/index";
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi");
			model.addAttribute("list", getItems());
			return "admin/customer/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if (file.isEmpty()) {
				customer.setPhoto("logo.png");
			} else {
				customer.setPhoto(System.currentTimeMillis() + file.getOriginalFilename());
				String path = app.getRealPath("/static/images/customers/" + customer.getPhoto());
				file.transferTo(new File(path));
			}
			session.save(customer);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công");
			model.addAttribute("model", new Customer());
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại");
		} finally {
			session.close();
		}
		model.addAttribute("status", 1);
		model.addAttribute("list", getItems());
		return "admin/customer/index";
	}

	@PostMapping("update")
	public String update(ModelMap model, @RequestParam("photo_file") MultipartFile file,
			@Valid @ModelAttribute("model") Customer customer, BindingResult errors) {
		model.addAttribute("status", 2);
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa lỗi");
			model.addAttribute("list", getItems());
			return "admin/customer/index";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if (!file.isEmpty()) {
				customer.setPhoto(System.currentTimeMillis() + file.getOriginalFilename());
				String path = app.getRealPath("/static/images/customers/" + customer.getPhoto());
				file.transferTo(new File(path));
			}
			session.update(customer);
			t.commit();
			model.addAttribute("message", "Cập nhập thành công");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhập thất bại");
		} finally {
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/customer/index";
	}

	@GetMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") String id) {
		Customer customer = cdao.findById(id);
		model.addAttribute("model", customer);
		model.addAttribute("status", 2);
		model.addAttribute("list", getItems());
		return "admin/customer/index";
	}

	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, @ModelAttribute("model") Customer customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if (odao.findByUser(customer).size() > 0) {
			model.addAttribute("message", "Xóa không thành công vì khách hàng này đã có đơn hàng");
			model.addAttribute("list", getItems());
			model.addAttribute("model", new Customer());
			return "admin/customer/index";
		}
		try {
			session.delete(customer);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
			model.addAttribute("model", new Customer());
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		} finally {
			session.close();
		}
		model.addAttribute("list", getItems());
		return "admin/customer/index";
	}

	public List<Customer> getItems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Customer";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		return list;
	}
	
	@RequestMapping("load")
	public String loadPage(ModelMap model,
	        @RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
	        @RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
	    Session session = factory.getCurrentSession();
	    String hql = "FROM Customer";
	    Query query = session.createQuery(hql);
	    query.setFirstResult(pageNo * pageSize);
	    query.setMaxResults(pageSize);
	    List<Product> list = query.list();

	    model.addAttribute("list", list);
	    return "nonlayout/admin/customer/table";
	}

	@ResponseBody
	@RequestMapping("count")
	public String getPageCount(@RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
	    Session session = factory.getCurrentSession();
	    String hql = "SELECT COUNT(c) FROM Customer c";
	    Query query = session.createQuery(hql);
	    Long count = (Long) query.uniqueResult();
	    double pages = Math.ceil((double) count / pageSize);
	    return String.valueOf(pages);
	}

}
