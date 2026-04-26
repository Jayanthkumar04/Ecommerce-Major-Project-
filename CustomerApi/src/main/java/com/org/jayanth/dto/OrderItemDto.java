package com.org.jayanth.dto;

public class OrderItemDto {

	private Long productId;
    private int quantity;
    private double unitPrice;
    private String imageUrl;
    private String name;
	public OrderItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItemDto(Long productId, int quantity, double unitPrice, String imageUrl,String name) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.imageUrl = imageUrl;
		this.name = name;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "OrderItemDto [productId=" + productId + ", quantity=" + quantity + ", unitPrice=" + unitPrice
				+ ", imageUrl=" + imageUrl + ", name=" + name + "]";
	}
    
}
