package com.demo.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.rbac.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
