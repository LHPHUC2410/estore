package com.estore.admin.controller;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.estore.entity.security.ActionRole;
import com.estore.entity.security.Role;
import com.estore.entity.security.WebAction;

@Transactional
@Controller
@RequestMapping("admin/web-action-role/")
public class WebActionRoleController {
    
    @Autowired
    SessionFactory factory;
    
    @RequestMapping("index")
    public String index() {
        return "admin/action/web-action-role";
    }
    
    @ResponseBody
    @RequestMapping("get-action-ids")
    public String getActionIds(@RequestParam("roleId") String roleId) {
        Session session = factory.getCurrentSession();
        String hql = "SELECT ar.webAction.id FROM ActionRole ar WHERE ar.role.id=:rid";
        Query query = session.createQuery(hql);
        query.setParameter("rid", roleId);
        List<Integer> list = query.list();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            return "[]";
        }
    }
    
    @ResponseBody
    @RequestMapping("update")
    public void update(@RequestParam("roleId") String roleId, 
                       @RequestParam("actionId") Integer actionId, 
                       @RequestParam("isChecked") boolean isChecked) {
        Session session = factory.getCurrentSession();
        String hql = "FROM ActionRole WHERE webAction.id=:aid AND role.id=:rid";
        Query query = session.createQuery(hql);
        query.setParameter("rid", roleId);
        query.setParameter("aid", actionId);

        try {
            ActionRole ar = (ActionRole) query.uniqueResult();
            
            if (isChecked && ar == null) {
                // Nếu checkbox được chọn và liên kết không tồn tại, tạo liên kết mới
                Role role = new Role();
                role.setId(roleId);
                
                WebAction action = new WebAction();
                action.setId(actionId);
                
                ar = new ActionRole();
                ar.setRole(role);
                ar.setWebAction(action);
                
                session.save(ar);
            } else if (!isChecked && ar != null) {
                // Nếu checkbox không được chọn và liên kết tồn tại, xóa liên kết
                session.delete(ar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
    }

    
    
    @ModelAttribute("actions")
    public List<WebAction> getWebActions() {
        Session session = factory.getCurrentSession();
        String hql = "FROM WebAction ORDER BY name";
        Query query = session.createQuery(hql);
        List<WebAction> list = query.list();
        return list;
    }
    
    @ModelAttribute("roles")
    public List<Role> getRoles() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Role ORDER BY name";
        Query query = session.createQuery(hql);
        List<Role> list = query.list();
        return list;
    }
}
