package com.org.jayanth.dto;

import java.time.LocalDateTime;

public class FilterRequest {
	
	private String email;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	 private String orderStatus;

	    private String invoiceStatus;

	    private String orderTrackingNum;

	    private String razorpayPaymentId;

	    private LocalDateTime deliveryStartDate;
	    private LocalDateTime deliveryEndDate;

	    private Double minPrice;
	    private Double maxPrice;
	    
	   private Long orderId;


	public FilterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

 
	
	public FilterRequest(String email, LocalDateTime startDate, LocalDateTime endDate, String orderStatus,
			String invoiceStatus, String orderTrackingNum, String razorpayPaymentId, LocalDateTime deliveryStartDate,
			LocalDateTime deliveryEndDate, Double minPrice, Double maxPrice,Long orderId) {
		super();
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orderStatus = orderStatus;
		this.invoiceStatus = invoiceStatus;
		this.orderTrackingNum = orderTrackingNum;
		this.razorpayPaymentId = razorpayPaymentId;
		this.deliveryStartDate = deliveryStartDate;
		this.deliveryEndDate = deliveryEndDate;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
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

	
	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	public String getInvoiceStatus() {
		return invoiceStatus;
	}



	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}



	public String getOrderTrackingNum() {
		return orderTrackingNum;
	}



	public void setOrderTrackingNum(String orderTrackingNum) {
		this.orderTrackingNum = orderTrackingNum;
	}



	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}



	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}



	public LocalDateTime getDeliveryStartDate() {
		return deliveryStartDate;
	}



	public void setDeliveryStartDate(LocalDateTime deliveryStartDate) {
		this.deliveryStartDate = deliveryStartDate;
	}



	public LocalDateTime getDeliveryEndDate() {
		return deliveryEndDate;
	}



	public void setDeliveryEndDate(LocalDateTime deliveryEndDate) {
		this.deliveryEndDate = deliveryEndDate;
	}



	public Double getMinPrice() {
		return minPrice;
	}



	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}



	public Double getMaxPrice() {
		return maxPrice;
	}



	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	@Override
	public String toString() {
		return "FilterRequest [email=" + email + ", startDate=" + startDate + ", endDate=" + endDate + ", orderStatus="
				+ orderStatus + ", invoiceStatus=" + invoiceStatus + ", orderTrackingNum=" + orderTrackingNum
				+ ", razorpayPaymentId=" + razorpayPaymentId + ", deliveryStartDate=" + deliveryStartDate
				+ ", deliveryEndDate=" + deliveryEndDate + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", orderId=" + orderId + "]";
	}
	
	

}
