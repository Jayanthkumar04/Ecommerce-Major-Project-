package com.jayanth.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jayanth.ecommerce.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order>{

	

	
	
}
