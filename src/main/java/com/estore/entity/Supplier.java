package com.estore.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Suppliers")
public class Supplier {
	@Id
	@NotBlank(message = "Mã nhà cung cấp không được để trống")
	String id;
	@NotBlank(message = "Tên nhà cung cấp không được để trống")
	String name;
	String logo;
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email không đúng định dạng")
	String email;
	@NotBlank(message = "Số điện thoại không được để trống")
	String phone;
	
	@OneToMany(mappedBy="supplier")
	Collection<Product> products;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
}
