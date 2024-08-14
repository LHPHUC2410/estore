package com.estore.admin.controller;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.CategoryDAO;
import com.estore.dao.ProductDAO;
import com.estore.entity.Category;

@Transactional
@Controller
@RequestMapping("admin/category/")
public class CategoryController {
    @Autowired
    SessionFactory factory;
    @Autowired
    CategoryDAO dao;
    @Autowired
    ProductDAO pdao;

    @RequestMapping("index")
    public String index(ModelMap model) {
        Category entity = new Category();

        model.addAttribute("model", entity);
        model.addAttribute("list", dao.findAll());

        return "admin/category/index";
    }

    @RequestMapping("insert")
    public String create(RedirectAttributes model, @Valid @ModelAttribute("model") Category entity, BindingResult errors) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Hãy nhập tên loại hàng");
            return "redirect:/admin/category/index";
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(entity);
            t.commit();
            model.addAttribute("message", "Thêm mới thành công!");
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "Cập nhật thất bại");
        } finally {
            session.close();
        }
        return "redirect:/admin/category/index";
    }
    @RequestMapping("edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Integer id) {
        Category entity = dao.findById(id);

        model.addAttribute("model", entity);
        model.addAttribute("list", dao.findAll());
        return "admin/category/index";
    }
    @RequestMapping("update")
    public String update(RedirectAttributes model, @Valid @ModelAttribute("model") Category entity, BindingResult errors) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Hãy nhập tên loại hàng");
            return "redirect:/admin/category/index";
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(entity);
            t.commit();
            model.addAttribute("message", "Cập nhật thành công!");
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "Cập nhật thất bại");
        } finally {
            session.close();
        }
        return "redirect:/admin/category/edit/" + entity.getId();
    }

    @RequestMapping("delete/{id}")
    public String delete(ModelMap model, @ModelAttribute("model") Category entity) {
        if (pdao.findByCategoryId(entity.getId()).size() > 0) {
            model.addAttribute("message", "Xóa không thành công vì loại hàng này đã có sản phẩm");
            model.addAttribute("model", new Category());
            model.addAttribute("list", dao.findAll());
            return "admin/category/index";
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(entity);
            t.commit();
            model.addAttribute("message", "Xóa thành công");
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "Xóa thất bại");
        } finally {
            session.close();
        }
        model.addAttribute("model", new Category());
        model.addAttribute("list", dao.findAll());
        return "admin/category/index";
        //return "redirect:/admin/category/index";
    }
}
