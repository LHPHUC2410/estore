package com.estore.entity.security;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Roles")
public class Role {
	@Id
	@NotBlank(message = "Mã vai trò không được trống")
	String id;
	@NotBlank(message = "Tên vai trò không được trống")
	String name;
		
	@OneToMany(mappedBy="role")
	Collection<ActionRole> actionRoles;
	
	@OneToMany(mappedBy="role")
	Collection<MasterRole> masterRoles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ActionRole> getActionRoles() {
		return actionRoles;
	}

	public void setActionRoles(Collection<ActionRole> actionRoles) {
		this.actionRoles = actionRoles;
	}

	public Collection<MasterRole> getMasterRoles() {
		return masterRoles;
	}

	public void setMasterRoles(Collection<MasterRole> masterRoles) {
		this.masterRoles = masterRoles;
	}
	
	@Override
	public boolean equals(Object obj) {
		Role r = (Role) obj;
		return this.id.equals(r.getId());
	}
}
