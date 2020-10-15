package com.sba3.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sba3.interviewtracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
