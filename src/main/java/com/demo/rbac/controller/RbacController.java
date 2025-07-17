package com.demo.rbac.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RbacController {

	private final Map<Integer, String> dataStore = new HashMap<>();
	private final Map<Integer, String> adminStore = new HashMap<>();
	private final Map<Integer, String> doctorStore = new HashMap<>();
	private final Map<Integer, String> donorStore = new HashMap<>();

	private int dataIdCount = 1;
	private int adminIdCount = 1;
	private int doctorIdCount = 1;
	private int donorIdCount = 1;

	public RbacController() {
		dataStore.put(dataIdCount++, "Sample User Record");
		adminStore.put(adminIdCount++, "Sample Admin Record");
		doctorStore.put(doctorIdCount++, "Sample Doctor Record");
		donorStore.put(donorIdCount++, "Sample Donor Record");
	}

	// User CRUD
	@GetMapping("/data")
	public Collection<String> getAllUsers() {
		return dataStore.values();
	}

	@PostMapping("/data")
	public String createUser(@RequestBody String newUser) {
		int id = dataIdCount++;
		dataStore.put(id, newUser);
		return "Created User with ID: " + id;
	}

	@DeleteMapping("/data/{id}")
	public String deleteUser(@PathVariable int id) {
		if (dataStore.containsKey(id)) {
			dataStore.remove(id);
			return "User Deleted With ID: " + id;
		} else
			return "User Not Found With ID: " + id;
	}

	// Admin CRUD

	@GetMapping("/admin")
	public Collection<String> getAllAdmins() {
		return adminStore.values();
	}

	@PostMapping("/admin")
	public String createAdmin(@RequestBody String newAdmin) {
		int id = adminIdCount++;
		adminStore.put(id, newAdmin);
		return "Created new admin with Id: " + id;
	}

	@DeleteMapping("/admin/{id}")
	public String deleteAdmin(@PathVariable int id) {
		if (adminStore.containsKey(id)) {
			adminStore.remove(id);
			return "Deleted admin with Id: " + id;
		} else {
			return "Admin not found with Id: " + id;
		}
	}

	// Doctor CRUD

	@GetMapping("/doctor")
	public Collection<String> getAllDoctors() {
		return doctorStore.values();
	}

	@PostMapping("/doctor")
	public String createDoctor(@RequestBody String newDoctor) {
		int id = doctorIdCount++;
		doctorStore.put(id, newDoctor);
		return "Created doctor successfully with Id: " + id;
	}

	@DeleteMapping("/doctor/{id}")
	public String deleteDoctor(@PathVariable int id) {
		if (doctorStore.containsKey(id)) {
			doctorStore.remove(id);
			return "Deleted doctor successfully with Id: " + id;
		} else {
			return "Doctor not found with Id: " + id;
		}
	}

	// Donor CRUD

	@GetMapping("/blood-donor")
	public Collection<String> getAllDonors() {
		return donorStore.values();
	}

	@PostMapping("/blood-donor")
	public String createBloodDonor(@RequestBody String newDonor) {
		int id = donorIdCount++;
		donorStore.put(id, newDonor);
		return "Added new blood-donor Successfully with Id: " + id;
	}

	@DeleteMapping("/blood-donor/{id}")
	public String deleteBloodDonor(@PathVariable int id) {
		if (donorStore.containsKey(id)) {
			donorStore.remove(id);
			return "Removed blood donor successfully with Id: " + id;
		} else {
			return "Blood donor not found with Id: " + id;
		}
	}

}
