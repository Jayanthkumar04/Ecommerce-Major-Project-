package com.org.jayanth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.jayanth.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{

	Users findByEmail(String email);
}
