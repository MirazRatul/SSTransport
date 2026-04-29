package com.sstransport.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reg_number", length = 45, nullable = false)
    private String regNumber;

    @Column(name = "vehicle_size", length = 1)
    private String vehicleSize;

    @Column(name = "capacity", length = 20)
    private String capacity;

    @Column(name = "assigned_driver")
    private Integer assignedDriver;

    @Column(name = "assigned_helper")
    private Integer assignedHelper;

    @Column(name = "reg_card", length = 255)
    private String regCard;

    @Column(name = "fitness_certificate", length = 255)
    private String fitnessCertificate;

    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

    @Column(name = "parts_fixed", length = 100)
    private String partsFixed;

    // Constructors
    public Vehicle() {}

    public Vehicle(String regNumber, String vehicleSize, String capacity,
                   Integer assignedDriver, Integer assignedHelper, String regCard,
                   String fitnessCertificate, LocalDate lastMaintenanceDate, String partsFixed) {
        this.regNumber = regNumber;
        this.vehicleSize = vehicleSize;
        this.capacity = capacity;
        this.assignedDriver = assignedDriver;
        this.assignedHelper = assignedHelper;
        this.regCard = regCard;
        this.fitnessCertificate = fitnessCertificate;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.partsFixed = partsFixed;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getVehicleSize() { return vehicleSize; }
    public void setVehicleSize(String vehicleSize) { this.vehicleSize = vehicleSize; }

    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }

    public Integer getAssignedDriver() { return assignedDriver; }
    public void setAssignedDriver(Integer assignedDriver) { this.assignedDriver = assignedDriver; }

    public Integer getAssignedHelper() { return assignedHelper; }
    public void setAssignedHelper(Integer assignedHelper) { this.assignedHelper = assignedHelper; }

    public String getRegCard() { return regCard; }
    public void setRegCard(String regCard) { this.regCard = regCard; }

    public String getFitnessCertificate() { return fitnessCertificate; }
    public void setFitnessCertificate(String fitnessCertificate) { this.fitnessCertificate = fitnessCertificate; }

    public LocalDate getLastMaintenanceDate() { return lastMaintenanceDate; }
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) { this.lastMaintenanceDate = lastMaintenanceDate; }

    public String getPartsFixed() { return partsFixed; }
    public void setPartsFixed(String partsFixed) { this.partsFixed = partsFixed; }
}
