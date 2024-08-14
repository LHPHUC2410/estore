package com.estore.entity.security;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="WebActions")
public class WebAction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Mã chức năng không được trống")
	Integer id;
	@NotBlank(message = "Tên chức năng không được trống")
	String name;
	@NotBlank(message = "Mô tả chức năng không được trống")
	String description;
	
	@OneToMany(mappedBy="webAction")
	Collection<ActionRole> actionRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ActionRole> getActionRoles() {
		return actionRoles;
	}

	public void setActionRoles(Collection<ActionRole> actionRoles) {
		this.actionRoles = actionRoles;
	}
	
	@Override
	public boolean equals(Object obj) {
		WebAction wa = (WebAction) obj;
		return this.getId().equals(wa.getId());
	}
}
