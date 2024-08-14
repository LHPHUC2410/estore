package com.estore.entity.security;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Masters")
public class Master {
	@Id
	String id;
	@NotBlank(message = "Không để trống mật khẩu")
	String password;
	@NotBlank(message = "Không để trống họ tên")
	String fullName;
	@NotBlank(message = "Không để trống email")
	@Email(message = "Email không đúng định dạng")
	String email;
	@NotBlank(message = "Không để trống số điện thoại")
	String mobile;
	
	@OneToMany(mappedBy="master")
	Collection<MasterRole> masterRoles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Collection<MasterRole> getMasterRoles() {
		return masterRoles;
	}

	public void setMasterRoles(Collection<MasterRole> masterRoles) {
		this.masterRoles = masterRoles;
	}
	
}
