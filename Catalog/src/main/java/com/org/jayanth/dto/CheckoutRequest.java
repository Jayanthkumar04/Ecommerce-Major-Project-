package com.org.jayanth.dto;

import java.util.List;

import com.org.jayanth.entity.OrderItems;
import com.org.jayanth.entity.ShippingAddress;
import com.org.jayanth.entity.User;

public class CheckoutRequest {

	private User user;
	
	private ShippingAddress shippingAddress;
	
	private List<OrderItemDto> orderItems;
	
	private Long totalQuantity;
	
	private Double totalPrice;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "CheckoutRequest [user=" + user + ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems
				+ ", totalQuantity=" + totalQuantity + ", totalPrice=" + totalPrice + "]";
	}
	
	
}
