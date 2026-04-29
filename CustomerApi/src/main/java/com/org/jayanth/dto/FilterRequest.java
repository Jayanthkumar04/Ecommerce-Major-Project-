package com.org.jayanth.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FilterRequest {
	
	private String email;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Long orderId;

	public FilterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterRequest(String email, LocalDate startDate, LocalDate endDate,Long orderId) {
		super();
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orderId=orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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
