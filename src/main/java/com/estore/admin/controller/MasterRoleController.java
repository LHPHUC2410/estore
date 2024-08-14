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

import com.estore.entity.security.Master;
import com.estore.entity.security.MasterRole;
import com.estore.entity.security.Role;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Controller
@RequestMapping("admin/master-role")
public class MasterRoleController {

    @Autowired
    SessionFactory factory;

    @RequestMapping("index")
    public String index() {
        return "admin/action/master-role";
    }

    @ResponseBody
    @RequestMapping("get-role-ids")
    public String getRoleIds(@RequestParam("masterId") String masterId) {
        Session session = factory.getCurrentSession();
        String hql = "SELECT role.id FROM MasterRole WHERE master.id=:mid";
        Query query = session.createQuery(hql);
        query.setParameter("mid", masterId);
        List<String> list = query.list();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }

    @ResponseBody
    @RequestMapping("update")
    public void update(@RequestParam("masterId") String masterId, @RequestParam("roleId") String roleId, @RequestParam("isChecked") boolean isChecked) {
        Session session = factory.getCurrentSession();
        String hql = "FROM MasterRole WHERE master.id=:mid AND role.id=:rid";
        Query query = session.createQuery(hql);
        query.setParameter("mid", masterId);
        query.setParameter("rid", roleId);

        try {
            MasterRole mr = (MasterRole) query.uniqueResult();
            if (isChecked && mr == null) {
                // Nếu checkbox được chọn và liên kết không tồn tại, tạo liên kết mới
                Master master = new Master();
                master.setId(masterId);

                Role role = new Role();
                role.setId(roleId);

                mr = new MasterRole();
                mr.setMaster(master);
                mr.setRole(role);

                session.save(mr);
            } else if (!isChecked && mr != null) {
                // Nếu checkbox không được chọn và liên kết tồn tại, xóa liên kết
                session.delete(mr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ModelAttribute("masters")
    public List<Master> getMasters() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Master";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Role";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
