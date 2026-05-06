package com.sstransport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(length = 50, nullable = false)
    private String id; // Firebase UID

    @Column(length = 60, nullable = false, unique = true)
    private String email;

    @Column(length = 45, nullable = true)
    private String name;

    @Column(length = 45, nullable = true)
    private String contact;

    @Column(name = "image_url", length = 255, nullable = true)
    private String imageUrl;

    @Column(length = 15, nullable = true)
    private String role;

    public Admin() {}

    public Admin(String id, String email, String name, String contact, String imageUrl, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}