package com.estore.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.estore.dao.CategoryDAO;
import com.estore.dao.SupplierDAO;
import com.estore.entity.Category;
import com.estore.entity.Supplier;

import org.springframework.web.servlet.HandlerInterceptor;

@Transactional
@Component
public class EShopInterceptor implements HandlerInterceptor{
	@Autowired
	CategoryDAO dao;
	@Autowired
	SupplierDAO sdao;
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		 ModelAndView modelAndView) throws Exception {
		List<Category> list =dao.findAll();
		request.setAttribute("cates",list);
		List<Supplier> listS =sdao.findAllSup();
		request.setAttribute("supps",listS);
		
	}
}
