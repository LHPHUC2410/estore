package com.estore.entity.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MasterRoles")
public class MasterRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	//String masterId	;
	//String roleId	;
	
	@ManyToOne
	@JoinColumn(name="masterId")
	Master master;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
