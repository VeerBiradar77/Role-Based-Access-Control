package com.demo.rbac.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private boolean isActive;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Permission> permissions;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_inheritance", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "inherited_role_id"))
	private Set<Role> inheritedRoles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Role> getInheritedRoles() {
		return inheritedRoles;
	}

	public void setInheritedRoles(Set<Role> inheritedRoles) {
		this.inheritedRoles = inheritedRoles;
	}
	
	

}
