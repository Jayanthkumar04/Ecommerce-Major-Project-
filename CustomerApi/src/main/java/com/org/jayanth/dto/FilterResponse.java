package com.org.jayanth.dto;

import java.time.LocalDateTime;
import java.util.List;


public class FilterResponse {
	
	private Long orderId;
	
	private String email;
	
	private LocalDateTime orderDate;
	
	private String orderStatus;

	private String orderTrackingNum;

	private String razorpayPaymentId;

	private LocalDateTime deliveryDate;
	
	 private String invoiceUrl;
	
	 private List<OrderItemDto> items;
	 
	

	public FilterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public FilterResponse(Long orderId, String email, LocalDateTime orderDate, String orderStatus,
			String orderTrackingNum, String razorpayPaymentId, LocalDateTime deliveryDate, String invoiceUrl,
			List<OrderItemDto> items) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderTrackingNum = orderTrackingNum;
		this.razorpayPaymentId = razorpayPaymentId;
		this.deliveryDate = deliveryDate;
		this.invoiceUrl = invoiceUrl;
		this.items = items;
	}




	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	
	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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



   


	public LocalDateTime getOrderDate() {
		return orderDate;
	}





	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}





	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}





	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	



	public Long getOrderId() {
		return orderId;
	}




	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


     

	public String getInvoiceUrl() {
		return invoiceUrl;
	}




	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}




	public List<OrderItemDto> getItems() {
		return items;
	}





	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}





	@Override
	public String toString() {
		return "FilterResponse [orderId=" + orderId + ", email=" + email + ", orderDate=" + orderDate + ", orderStatus="
				+ orderStatus + ", orderTrackingNum=" + orderTrackingNum + ", razorpayPaymentId=" + razorpayPaymentId
				+ ", deliveryDate=" + deliveryDate + ", invoiceUrl=" + invoiceUrl + ", items=" + items + "]";
	}





	
	

}
