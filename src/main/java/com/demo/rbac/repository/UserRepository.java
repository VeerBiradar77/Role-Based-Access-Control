package com.demo.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.rbac.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
