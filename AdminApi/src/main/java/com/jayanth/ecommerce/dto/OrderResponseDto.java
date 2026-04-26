package com.jayanth.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {

	private Long orderId;
    private String orderTrackingNum;
    private String email;
    private Double totalPrice;
    private LocalDateTime deliveryDate;
    private Long totalQuantity;
    
	private List<OrderItemDto> items;
	public OrderResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderResponseDto(Long orderId, String orderTrackingNum, String email, Double totalPrice,
			LocalDateTime deliveryDate, Long totalQuantity, List<OrderItemDto> items) {
		super();
		this.orderId = orderId;
		this.orderTrackingNum = orderTrackingNum;
		this.email = email;
		this.totalPrice = totalPrice;
		this.deliveryDate = deliveryDate;
		this.totalQuantity = totalQuantity;
		this.items = items;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderTrackingNum() {
		return orderTrackingNum;
	}
	public void setOrderTrackingNum(String orderTrackingNum) {
		this.orderTrackingNum = orderTrackingNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public List<OrderItemDto> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}
	
	
	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Override
	public String toString() {
		return "OrderResponseDto [orderId=" + orderId + ", orderTrackingNum=" + orderTrackingNum + ", email=" + email
				+ ", totalPrice=" + totalPrice + ", deliveryDate=" + deliveryDate + ", totalQuantity=" + totalQuantity
				+ ", items=" + items + "]";
	}
    
    

}
