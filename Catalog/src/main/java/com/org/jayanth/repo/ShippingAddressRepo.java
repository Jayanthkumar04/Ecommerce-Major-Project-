package com.org.jayanth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.jayanth.entity.ShippingAddress;

@Repository
public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, Long>{

}
