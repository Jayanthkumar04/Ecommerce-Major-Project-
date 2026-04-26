package com.jayanth.ecommerce.dto;

public class AdminDashboardDto {

    private Integer totalCustomers;
	
	private Integer totalOrders;
	
	private Double totalAmountCollected;
	
	private Long totalQuantity;
	
	
	

	public AdminDashboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminDashboardDto(Integer totalCustomers, Integer totalOrders, Double totalAmountCollected,
			Long totalQuantity) {
		super();
		this.totalCustomers = totalCustomers;
		this.totalOrders = totalOrders;
		this.totalAmountCollected = totalAmountCollected;
		this.totalQuantity = totalQuantity;
	}

	public Integer getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(Integer totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public Integer getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Integer totalOrders) {
		this.totalOrders = totalOrders;
	}

	public Double getTotalAmountCollected() {
		return totalAmountCollected;
	}

	public void setTotalAmountCollected(Double totalAmountCollected) {
		this.totalAmountCollected = totalAmountCollected;
	}

    

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Override
	public String toString() {
		return "AdminDashboardDto [totalCustomers=" + totalCustomers + ", totalOrders=" + totalOrders
				+ ", totalAmountCollected=" + totalAmountCollected + ", totalQuantity=" + totalQuantity + "]";
	}
	
	

}
