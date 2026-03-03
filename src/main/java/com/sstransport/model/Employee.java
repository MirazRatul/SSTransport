package com.sstransport.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "contact", length = 15)
    private String contact;

    @Column(name = "nid_no", length = 15)
    private String nidNo;

    @Column(name = "role", length = 10)
    private String role;

    @Column(name = "driving_license_no", length = 25)
    private String drivingLicenseNo;

    @Column(name = "nid_pic", length = 255)
    private String nidPic;

    @Column(name = "driving_license_img", length = 255)
    private String drivingLicenseImg;

    // Constructors
    public Employee() {}

    public Employee(String name, String image, String contact,
                    String nidNo, String role, String drivingLicenseNo,
                    String nidPic, String drivingLicenseImg) {
        this.name = name;
        this.image = image;
        this.contact = contact;
        this.nidNo = nidNo;
        this.role = role;
        this.drivingLicenseNo = drivingLicenseNo;
        this.nidPic = nidPic;
        this.drivingLicenseImg = drivingLicenseImg;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getNidNo() { return nidNo; }
    public void setNidNo(String nidNo) { this.nidNo = nidNo; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDrivingLicenseNo() { return drivingLicenseNo; }
    public void setDrivingLicenseNo(String drivingLicenseNo) { this.drivingLicenseNo = drivingLicenseNo; }

    public String getNidPic() { return nidPic; }
    public void setNidPic(String nidPic) { this.nidPic = nidPic; }

    public String getDrivingLicenseImg() { return drivingLicenseImg; }
    public void setDrivingLicenseImg(String drivingLicenseImg) { this.drivingLicenseImg = drivingLicenseImg; }
}
