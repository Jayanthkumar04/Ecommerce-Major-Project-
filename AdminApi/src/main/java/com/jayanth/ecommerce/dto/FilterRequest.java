package com.jayanth.ecommerce.dto;

import java.time.LocalDateTime;

public class FilterRequest {
	
	private String email;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private Long orderId;

	public FilterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterRequest(String email, LocalDateTime startDate, LocalDateTime endDate,Long orderId) {
		super();
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "FilterRequest [email=" + email + ", startDate=" + startDate + ", endDate=" + endDate + ", orderId="
				+ orderId + "]";
	}
	
	

}
