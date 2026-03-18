package com.org.jayanth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	
	private String imageUrl;
	
	private Double unitPrice;
	
	private Integer quantity;
	
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name="order_id",nullable=false)
	private Order order;

	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItems(Long orderItemId, String imageUrl, Double unitPrice, Integer quantity, Long productId,
			Order order) {
		super();
		this.orderItemId = orderItemId;
		this.imageUrl = imageUrl;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.productId = productId;
		this.order = order;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItems [orderItemId=" + orderItemId + ", imageUrl=" + imageUrl + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + ", productId=" + productId + ", order=" + order + "]";
	}
	
	
	
}
