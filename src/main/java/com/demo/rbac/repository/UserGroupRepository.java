package com.demo.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.rbac.model.UserGroups;

public interface UserGroupRepository extends JpaRepository<UserGroups, Long> {

}
