package com.org.jayanth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.jayanth.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	Order findByRazorPaymentId(String id);
	
	
    Order findByRazorOrderId(String razorpayOrderId);
}
