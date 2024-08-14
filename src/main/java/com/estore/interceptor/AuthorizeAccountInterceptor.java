package com.estore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import com.estore.entity.security.Master;

@Transactional
public class AuthorizeAccountInterceptor implements HandlerInterceptor{
	@Autowired
	SessionFactory factory;
	
	@SuppressWarnings("rawtypes")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		Master master = (Master) httpSession.getAttribute("master");
		
		String cpath = request.getContextPath();
		String url = request.getRequestURI().replace(cpath, "");
		
		// Kiểm soát đăng nhập
		if(master == null){
			httpSession.setAttribute("AdminBackUrl", url);
			response.sendRedirect(cpath+"/admin/home/login");
			return false;
		}
		
		String actionName = url.replaceAll("/admin/", "").replaceAll(".jsp", "");
		
		// Kiểm tra action này có tồn tại trong WebAction hay không, nếu không thì cho phép truy cập
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(wa) FROM WebAction wa WHERE wa.name=:an";
		Query query = session.createQuery(hql);
		query.setParameter("an", actionName);
		Long count = (Long) query.uniqueResult();
		if(count == 0) {
			return true;
		}
		
		// Kiểm tra xem master có được phân quyền sử dụng action này hay không
		hql = "SELECT COUNT(ar) FROM ActionRole ar WHERE ar.webAction.name=:an AND ar.role.id IN (SELECT role.id FROM MasterRole WHERE master.id=:mid)";
		query = session.createQuery(hql);
		query.setParameter("an", actionName);
		query.setParameter("mid", master.getId());
		count = (Long) query.uniqueResult();
		if(count > 0) {
			return true;
		}
		
		// Không có quyền truy cập
		httpSession.setAttribute("AdminBackUrl", url);
		response.sendRedirect(cpath+"/admin/home/unauthorized");
		return false;
	}
}
