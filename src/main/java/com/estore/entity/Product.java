package com.estore.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
@Entity
@Table(name="Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NotBlank(message = "Không được để trống tên sản phẩm")
	String name;
	@NotBlank(message = "Không được để trống tóm tắt")
	String unitBrief;
	@NotNull(message ="Không được để trống giá")
	Double unitPrice;
	String image;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past(message = "Ngày sản xuất phải trong quá khứ")
	@NotNull(message ="Không được để trống NSX")
	Date productDate;
	Boolean available;
	String description;
	//Integer categoryId;
	//String supplierId;
	@NotNull(message ="Không được để trống số lượng")
	Integer quantity;
	@NotNull(message ="Không được để trống giảm giá")
	@DecimalMin(value = "0", message = "Giảm giá từ 0-1")
	@DecimalMax(value = "1", message = "Giảm giá từ 0-1")
	Double discount;
	Boolean special;
	Boolean latest;
	Integer views;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	Category category;
	
	@ManyToOne
	@JoinColumn(name="supplierId")
	Supplier supplier;
	
	@OneToMany(mappedBy="product")
	Collection<OrderDetails> orderDetails;
	

	public Product() {
		this.views = 0;
	}
	
	public String dateToString() 
	{
		return DateFormat.getInstance().format(this.productDate);
	}

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

	public String getUnitBrief() {
		return unitBrief;
	}

	public void setUnitBrief(String unitBrief) {
		this.unitBrief = unitBrief;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Boolean getSpecial() {
		return special;
	}

	public void setSpecial(Boolean special) {
		this.special = special;
	}

	public Boolean getLatest() {
		return latest;
	}

	public void setLatest(Boolean latest) {
		this.latest = latest;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Collection<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}

