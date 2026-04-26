package com.org.jayanth.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.jayanth.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order>{

	Order findByRazorpayPaymentId(String id);
	
	
    Order findByRazorpayOrderId(String razorpayOrderId);
    
    @Query("SELECT o FROM Order o WHERE o.deliveryDate BETWEEN :start AND :end AND o.orderStatus=:status")
    Page<Order> findOrdersForDeliveryToday(
    		
    		@Param("start") LocalDateTime start,
    		@Param("end")  LocalDateTime end,
    		@Param("status") String status,
    		Pageable pageable
    		);
    
    
    Page<Order> findByOrderStatus(String status,Pageable pageable);
}
