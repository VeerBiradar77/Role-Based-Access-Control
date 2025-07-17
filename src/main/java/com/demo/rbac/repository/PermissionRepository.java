package com.demo.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import com.demo.rbac.model.AccessLevel;
import com.demo.rbac.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	// direct role permission check
//	@Query("""
//
//			    SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END
//			    FROM User u
//			    JOIN u.roles r
//			    JOIN r.permissions p
//			    WHERE u.userName = :username
//			      AND u.isActive = true
//			      AND r.isActive = true
//			      AND p.isActive = true
//			      AND :path LIKE p.apiPath
//			      AND p.accessLevel = :level
//			""")
//	boolean hasDirectPermission(@Param("username") String username, @Param("path") String path,
//			@Param("level") AccessLevel level);
//
//	// one-level inherited roles permission check
//	@Query("""
//			    SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END
//			    FROM User u
//			    JOIN u.roles r
//			    JOIN r.inheritedRoles ir
//			    JOIN ir.permissions p
//			    WHERE u.userName = :username
//			      AND u.isActive = true
//			      AND r.isActive = true
//			      AND ir.isActive = true
//			      AND p.isActive = true
//			      AND :path LIKE p.apiPath
//			      AND p.accessLevel = :level
//			""")
//	boolean hasInheritedPermission(@Param("username") String username, @Param("path") String path,
//			@Param("level") AccessLevel level);

}
