package com.org.jayanth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.jayanth.entity.OrderItems;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItems, Long> {

}
