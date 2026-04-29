package com.sstransport.dto;

import java.time.LocalDate;

public class VehicleDetailDTO {

    private Integer vehicleId;
    private String regNumber;
    private String vehicleSize;
    private String capacity;
    private String regCard;
    private String fitnessCertificate;
    private LocalDate lastMaintenanceDate;
    private String partsFixed;
    
    // Driver details
    private Integer assignedDriverId;
    private String assignedDriverName;
    private String assignedDriverRole;
    private String assignedDriverImage;
    
    // Helper details
    private Integer assignedHelperId;
    private String assignedHelperName;
    private String assignedHelperRole;
    private String assignedHelperImage;

    // Constructor
    public VehicleDetailDTO(Integer vehicleId, String regNumber, String vehicleSize, String capacity,
                            String regCard, String fitnessCertificate, LocalDate lastMaintenanceDate,
                            String partsFixed, Integer assignedDriverId, String assignedDriverName,
                            String assignedDriverRole, String assignedDriverImage, Integer assignedHelperId,
                            String assignedHelperName, String assignedHelperRole, String assignedHelperImage) {
        this.vehicleId = vehicleId;
        this.regNumber = regNumber;
        this.vehicleSize = vehicleSize;
        this.capacity = capacity;
        this.regCard = regCard;
        this.fitnessCertificate = fitnessCertificate;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.partsFixed = partsFixed;
        this.assignedDriverId = assignedDriverId;
        this.assignedDriverName = assignedDriverName;
        this.assignedDriverRole = assignedDriverRole;
        this.assignedDriverImage = assignedDriverImage;
        this.assignedHelperId = assignedHelperId;
        this.assignedHelperName = assignedHelperName;
        this.assignedHelperRole = assignedHelperRole;
        this.assignedHelperImage = assignedHelperImage;
    }

    // Getters & Setters
    public Integer getVehicleId() { return vehicleId; }
    public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getVehicleSize() { return vehicleSize; }
    public void setVehicleSize(String vehicleSize) { this.vehicleSize = vehicleSize; }

    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }

    public String getRegCard() { return regCard; }
    public void setRegCard(String regCard) { this.regCard = regCard; }

    public String getFitnessCertificate() { return fitnessCertificate; }
    public void setFitnessCertificate(String fitnessCertificate) { this.fitnessCertificate = fitnessCertificate; }

    public LocalDate getLastMaintenanceDate() { return lastMaintenanceDate; }
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) { this.lastMaintenanceDate = lastMaintenanceDate; }

    public String getPartsFixed() { return partsFixed; }
    public void setPartsFixed(String partsFixed) { this.partsFixed = partsFixed; }

    public Integer getAssignedDriverId() { return assignedDriverId; }
    public void setAssignedDriverId(Integer assignedDriverId) { this.assignedDriverId = assignedDriverId; }

    public String getAssignedDriverName() { return assignedDriverName; }
    public void setAssignedDriverName(String assignedDriverName) { this.assignedDriverName = assignedDriverName; }

    public String getAssignedDriverRole() { return assignedDriverRole; }
    public void setAssignedDriverRole(String assignedDriverRole) { this.assignedDriverRole = assignedDriverRole; }

    public String getAssignedDriverImage() { return assignedDriverImage; }
    public void setAssignedDriverImage(String assignedDriverImage) { this.assignedDriverImage = assignedDriverImage; }

    public Integer getAssignedHelperId() { return assignedHelperId; }
    public void setAssignedHelperId(Integer assignedHelperId) { this.assignedHelperId = assignedHelperId; }

    public String getAssignedHelperName() { return assignedHelperName; }
    public void setAssignedHelperName(String assignedHelperName) { this.assignedHelperName = assignedHelperName; }

    public String getAssignedHelperRole() { return assignedHelperRole; }
    public void setAssignedHelperRole(String assignedHelperRole) { this.assignedHelperRole = assignedHelperRole; }

    public String getAssignedHelperImage() { return assignedHelperImage; }
    public void setAssignedHelperImage(String assignedHelperImage) { this.assignedHelperImage = assignedHelperImage; }
}
