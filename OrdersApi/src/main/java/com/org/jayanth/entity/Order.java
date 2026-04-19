package com.org.jayanth.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDateTime;

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

    private String orderTrackingNum;

    private Long totalQuantity;

    private Double totalPrice;

    private String orderStatus;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

    private LocalDateTime deliveryDate;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String email;
    
    private String invoiceUrl;
    
    private String invoiceStatus;

    @ManyToOne
    @JoinColumn(name="addressId", nullable = false)
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public Order() {}

    
    


	public Order(Long orderId, String orderTrackingNum, Long totalQuantity, Double totalPrice, String orderStatus,
			LocalDateTime dateCreated, LocalDateTime lastUpdated, LocalDateTime deliveryDate, String razorpayOrderId,
			String razorpayPaymentId, String email, String invoiceUrl, String invoiceStatus,
			ShippingAddress shippingAddress, List<OrderItems> orderItems) {
		super();
		this.orderId = orderId;
		this.orderTrackingNum = orderTrackingNum;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.deliveryDate = deliveryDate;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.email = email;
		this.invoiceUrl = invoiceUrl;
		this.invoiceStatus = invoiceStatus;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
	}





	// ================= GETTERS & SETTERS =================

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderTrackingNum() {
		return orderTrackingNum;
	}

	public void setOrderTrackingNum(String orderTrackingNum) {
		this.orderTrackingNum = orderTrackingNum;
	}

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	
	
	public String getInvoiceUrl() {
		return invoiceUrl;
	}





	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}





	public String getInvoiceStatus() {
		return invoiceStatus;
	}





	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}





	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderTrackingNum=" + orderTrackingNum + ", totalQuantity="
				+ totalQuantity + ", totalPrice=" + totalPrice + ", orderStatus=" + orderStatus + ", dateCreated="
				+ dateCreated + ", lastUpdated=" + lastUpdated + ", deliveryDate=" + deliveryDate + ", razorpayOrderId="
				+ razorpayOrderId + ", razorpayPaymentId=" + razorpayPaymentId + ", email=" + email + ", invoiceUrl="
				+ invoiceUrl + ", invoiceStatus=" + invoiceStatus + ", shippingAddress=" + shippingAddress
				+ ", orderItems=" + orderItems + "]";
	}

    
}