package com.estore.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Customers")
public class Customer {
	@Id
	@NotBlank(message = "Mã khách hàng không được để trống")
	String id;
	@NotBlank(message = "Mật khẩu không được để trống")
	String password;
	@NotBlank(message = "Họ và tên không được để trống")
	String fullname;
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Không đúng định dạng email")
	String email;
	String photo;
	@NotNull(message = "Hãy chọn trạng thái tài khoản")
	Boolean activated;
		
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	Collection<Order> orders;

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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", password=" + password + ", fullname=" + fullname + ", email=" + email
				+ ", photo=" + photo + ", activated=" + activated + ", orders=" + orders + "]";
	}
	
}
