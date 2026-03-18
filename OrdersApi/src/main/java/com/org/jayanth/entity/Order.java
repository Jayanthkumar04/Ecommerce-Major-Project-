package com.org.jayanth.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private String orderTrackingNum;
	
	private Long totalQuantity;
	
	private Double totalPrice;
	
	private String orderStatus;
	
	
	private LocalDateTime dateCreated;

	private LocalDateTime lastUpdated;
	
	private String razorPaymentId;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name="addressId",nullable = false)
	private ShippingAddress shippingAddress;
	
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;

	
	

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Order(Long orderId, String orderTrackingNum, Long totalQuantity, Double totalPrice, String orderStatus,
			LocalDateTime dateCreated, LocalDateTime lastUpdated, String razorPaymentId,String email,
			ShippingAddress shippingAddress, List<OrderItems> orderItems) {
		super();
		this.orderId = orderId;
		this.orderTrackingNum = orderTrackingNum;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.razorPaymentId = razorPaymentId;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
		this.email = email;
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



	public Long getTotalQuantity() {
		return totalQuantity;
	}



	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}



	public Double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	public LocalDateTime getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}



	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}



	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}



	public String getRazorPaymentId() {
		return razorPaymentId;
	}



	public void setRazorPaymentId(String razorPaymentId) {
		this.razorPaymentId = razorPaymentId;
	}



	


	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}



	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}



	public List<OrderItems> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderTrackingNum=" + orderTrackingNum + ", totalQuantity="
				+ totalQuantity + ", totalPrice=" + totalPrice + ", orderStatus=" + orderStatus + ", dateCreated="
				+ dateCreated + ", lastUpdated=" + lastUpdated + ", razorPaymentId=" + razorPaymentId + ", email="
				+ email + ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems + "]";
	}
	
	
	
	
	
}
