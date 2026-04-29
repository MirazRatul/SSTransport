package com.sstransport.dto;

public class VehicleCrewDTO {

    // Driver
    private Integer driverId;
    private String driverName;
    private String driverContact;
    private String driverRole;

    // Helper
    private Integer helperId;
    private String helperName;
    private String helperContact;
    private String helperRole;

    public VehicleCrewDTO(Integer driverId, String driverName, String driverContact, String driverRole,
                          Integer helperId, String helperName, String helperContact, String helperRole) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.driverRole = driverRole;
        this.helperId = helperId;
        this.helperName = helperName;
        this.helperContact = helperContact;
        this.helperRole = helperRole;
    }

    // Getters
    public Integer getDriverId() { return driverId; }
    public String getDriverName() { return driverName; }
    public String getDriverContact() { return driverContact; }
    public String getDriverRole() { return driverRole; }

    public Integer getHelperId() { return helperId; }
    public String getHelperName() { return helperName; }
    public String getHelperContact() { return helperContact; }
    public String getHelperRole() { return helperRole; }
}