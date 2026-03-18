package com.jayanth.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdminDashboard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer totalCustomers;
	
	private Integer totalOrders;
	
	private Double totalAmountCollected;
	
	private Integer totalProducts;

	public AdminDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminDashboard(Long id, Integer totalCustomers, Integer totalOrders, Double totalAmountCollected,
			Integer totalProducts) {
		super();
		this.id = id;
		this.totalCustomers = totalCustomers;
		this.totalOrders = totalOrders;
		this.totalAmountCollected = totalAmountCollected;
		this.totalProducts = totalProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(Integer totalProducts) {
		this.totalProducts = totalProducts;
	}

	@Override
	public String toString() {
		return "AdminDashboard [id=" + id + ", totalCustomers=" + totalCustomers + ", totalOrders=" + totalOrders
				+ ", totalAmountCollected=" + totalAmountCollected + ", totalProducts=" + totalProducts + "]";
	}
	
	
	
	
}
