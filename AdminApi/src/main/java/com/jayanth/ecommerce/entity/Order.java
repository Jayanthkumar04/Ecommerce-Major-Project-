package com.jayanth.ecommerce.entity;

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
	
	
	private Integer totalQuantity;
	
	private Double totalPrice;
	
	
	
	private LocalDateTime dateCreated;

	private LocalDateTime lastUpdated;
	
	
	private String email;
	
		
	

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Order(Long orderId, String orderTrackingNum, Integer totalQuantity, Double totalPrice, String orderStatus,
			LocalDateTime dateCreated, LocalDateTime lastUpdated, String razorPaymentId,String email
			) {
		super();
		this.orderId = orderId;
		
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.email = email;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	


	public Integer getTotalQuantity() {
		return totalQuantity;
	}



	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}



	public Double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalQuantity="
				+ totalQuantity + ", totalPrice=" + totalPrice + ", dateCreated="
				+ dateCreated + ", lastUpdated=" + lastUpdated +  ", email="
				+ email + "]";
	}
	
	
	
	
	
}
