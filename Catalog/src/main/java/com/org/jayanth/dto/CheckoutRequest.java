package com.org.jayanth.dto;

import java.util.List;

import com.org.jayanth.entity.ShippingAddress;

public class CheckoutRequest {

	
	private ShippingAddress shippingAddress;
	
	private String email;
	
	private List<OrderItemDto> orderItems;
	
	private Long totalQuantity;
	
	private Double totalPrice;

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
 
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CheckoutRequest [email=" + email + ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems
				+ ", totalQuantity=" + totalQuantity + ", totalPrice=" + totalPrice + "]";
	}
	
	
}
