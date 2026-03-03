package com.sstransport.controller;

import com.sstransport.model.Admin;
import com.sstransport.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    // Get all admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable String id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Create new admin
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        // Check required fields
        if (admin.getId() == null || admin.getId().isEmpty() ||
            admin.getEmail() == null || admin.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Check uniqueness
        if (adminRepository.existsById(admin.getId()) || adminRepository.existsByEmail(admin.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }

        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // Update admin
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String id, @RequestBody Admin adminDetails) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (!optionalAdmin.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Admin admin = optionalAdmin.get();

        if (adminDetails.getEmail() != null && !adminDetails.getEmail().isEmpty()) admin.setEmail(adminDetails.getEmail());
        if (adminDetails.getName() != null) admin.setName(adminDetails.getName());
        if (adminDetails.getContact() != null) admin.setContact(adminDetails.getContact());
        if (adminDetails.getImageUrl() != null) admin.setImageUrl(adminDetails.getImageUrl());

        Admin updatedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    // Delete admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        if (!adminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adminRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}