package com.jayanth.ecommerce.entities;

import java.math.BigDecimal;
import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String 	name;
	
	private String description;
	
	private String title;
	
	private BigDecimal unitPrice;
	
	private String imageUrl;
	
	private boolean active;
	
	private int unitsInStock;
	
	private Date dateCreated;
	
	private Date lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="category_id",nullable = false)
	private ProductCategory category;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long productId, String name, String description, String title, BigDecimal unitPrice, String imageUrl,
			boolean active, int unitsInStock, Date dateCreated, Date lastUpdated, ProductCategory category) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.title = title;
		this.unitPrice = unitPrice;
		this.imageUrl = imageUrl;
		this.active = active;
		this.unitsInStock = unitsInStock;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.category = category;
	}

	
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", title="
				+ title + ", unitPrice=" + unitPrice + ", imageUrl=" + imageUrl + ", active=" + active
				+ ", unitsInStock=" + unitsInStock + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated
				+ ", category=" + category + "]";
	}
	
	
	
	
}
