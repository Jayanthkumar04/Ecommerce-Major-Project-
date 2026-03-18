package com.org.jayanth.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ShippingAddress {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	private Long houseNum;
	
	private String street;
	
	private String city;
	
	private String  state;
	
	private Long zipcode;
	
	private String country;

	@OneToMany(mappedBy = "shippingAddress")
	private List<Order> orders;
	
	public ShippingAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ShippingAddress(Long addressId, Long houseNum, String street, String city, String state, Long zipcode,
			String country, List<Order> orders) {
		super();
		this.addressId = addressId;
		this.houseNum = houseNum;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.orders = orders;
	}



	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Long houseNum) {
		this.houseNum = houseNum;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public List<Order> getOrders() {
		return orders;
	}



	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	@Override
	public String toString() {
		return "ShippingAddress [addressId=" + addressId + ", houseNum=" + houseNum + ", street=" + street + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + ", orders=" + orders
				+ "]";
	}
	
	
	
	
}
