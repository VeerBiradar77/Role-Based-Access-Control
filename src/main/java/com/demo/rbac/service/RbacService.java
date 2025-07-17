package com.demo.rbac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rbac.model.AccessLevel;
import com.demo.rbac.model.Permission;
import com.demo.rbac.model.Role;
import com.demo.rbac.model.User;
//import com.demo.rbac.repository.PermissionRepository;
import com.demo.rbac.repository.UserRepository;

@Service
public class RbacService {

	@Autowired
	private UserRepository userRepository;

	public boolean hasAccess(String username, String path, String method) {
		User user = userRepository.findByUserName(username);
		if (user == null || !user.isActive()) {
			System.out.println("User not active or not found");
			return false;
		}

		// determine access level by http method
		AccessLevel level = method.equalsIgnoreCase("GET") ? AccessLevel.READ : AccessLevel.WRITE;
		
		// Check permissions recursively through user roles
		for (Role role : user.getRoles()) {
			if (hasPermissionRecursive(role, path, level)) {
				System.out.println("Permission granted by role: " + role.getName());
				return true;
			} else {
				System.out.println("No permission in role: " + role.getName());
			}
		}
		return false;
	}

	// recursively check this role and inherited roles for matching permission
	private boolean hasPermissionRecursive(Role role, String path, AccessLevel level) {
		if (!role.isActive()) {
			return false;
		}
		// check this role permission
		for (Permission permission : role.getPermissions()) {
			if (permission.isActive() && path.matches(permission.getApiPath())
					&& permission.getAccessLevel() == level) {
				System.out.println("Matched!");
				return true;
			}
		}
		// recursively check inherited roles if any
		if (role.getInheritedRoles() != null) {
			for (Role inherited : role.getInheritedRoles()) {
				if (hasPermissionRecursive(inherited, path, level)) {
					return true;
				}
			}
		}
		return false;
	}
	
//    @Autowired
//    private PermissionRepository permissionRepository;
//
//    public boolean hasAccess(String username, String path, String method) {
//        //access level based on HTTP method
//        AccessLevel level = method.equalsIgnoreCase("GET")
//            ? AccessLevel.READ
//            : AccessLevel.WRITE;
//
//        // run both direct + inherited checks
//        boolean direct = permissionRepository.hasDirectPermission(username, path, level);
//        boolean inherited = permissionRepository.hasInheritedPermission(username, path, level);
//
//        System.out.println("Direct: " + direct + ", Inherited: " + inherited);
//
//        return direct || inherited;
//    }


}
