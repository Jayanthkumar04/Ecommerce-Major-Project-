package com.jayanth.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayanth.ecommerce.entity.AdminDashboard;


@Repository
public interface AdminDashboardRepo extends JpaRepository<AdminDashboard,Long>{

	
}
