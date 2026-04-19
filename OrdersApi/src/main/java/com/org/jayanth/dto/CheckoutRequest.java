package com.org.jayanth.dto;

import java.util.List;

import com.org.jayanth.entity.ShippingAddress;
import com.org.jayanth.entity.User;

public class CheckoutRequest {

	
	private ShippingAddress shippingAddress;
	
	private User user;
	
	private List<OrderItemDto> orderItems;
	
	private Long totalQuantity;
	
	private Double totalPrice;

	

	public CheckoutRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckoutRequest(ShippingAddress shippingAddress, User user, List<OrderItemDto> orderItems,
			Long totalQuantity, Double totalPrice) {
		super();
		this.shippingAddress = shippingAddress;
		this.user = user;
		this.orderItems = orderItems;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
	}



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
		return "CheckoutRequest [shippingAddress=" + shippingAddress + ", user=" + user + ", orderItems=" + orderItems
				+ ", totalQuantity=" + totalQuantity + ", totalPrice=" + totalPrice + "]";
	}
	
	
}
