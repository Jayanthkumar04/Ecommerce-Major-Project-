package com.org.jayanth.dto;

public class CheckoutResponse {

	private Long orderId;
	
	private String razorpayId;
	
	private String orderTrackingNum;
	
	private Double amount;

	
	public CheckoutResponse() {
		super();
		// TODO Auto-generated constructor stub
	}




	public CheckoutResponse(Long orderId, String razorpayId, String orderTrackingNum, Double amount) {
		super();
		this.orderId = orderId;
		this.razorpayId = razorpayId;
		this.orderTrackingNum = orderTrackingNum;
		this.amount = amount;
	}

	
	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getRazorpayId() {
		return razorpayId;
	}

	public void setRazorpayId(String razorpayId) {
		this.razorpayId = razorpayId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderTrackingNum() {
		return orderTrackingNum;
	}




	public void setOrderTrackingNum(String orderTrackingNum) {
		this.orderTrackingNum = orderTrackingNum;
	}






	@Override
	public String toString() {
		return "CheckoutResponse [orderId=" + orderId + ", razorpayId=" + razorpayId + ", orderTrackingNum="
				+ orderTrackingNum + ", amount=" + amount + "]";
	}
	
	
}
