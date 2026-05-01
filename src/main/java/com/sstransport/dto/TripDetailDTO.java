package com.sstransport.dto;

import java.time.LocalDateTime;

public class TripDetailDTO {

    private Integer id;
    private LocalDateTime date;
    private String pickupDest;
    private String dropDest;
    private String clientName;
    private String clientContact;

    private Integer driverId;
    private String driverName;
    private String driverRole;
    private String driverImage;

    private Integer helperId;
    private String helperName;
    private String helperRole;
    private String helperImage;

    private Integer vehicleId;
    private String vehicleRegNumber;
    private String status;
    private Integer fare;
    private String goodsType;

    public TripDetailDTO(Integer id, LocalDateTime date, String pickupDest, String dropDest,
                         String clientName, String clientContact,
                         Integer driverId, String driverName, String driverRole, String driverImage,
                         Integer helperId, String helperName, String helperRole, String helperImage,
                         Integer vehicleId, String vehicleRegNumber, String status, Integer fare, String goodsType) {
        this.id = id;
        this.date = date;
        this.pickupDest = pickupDest;
        this.dropDest = dropDest;
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverRole = driverRole;
        this.driverImage = driverImage;
        this.helperId = helperId;
        this.helperName = helperName;
        this.helperRole = helperRole;
        this.helperImage = helperImage;
        this.vehicleId = vehicleId;
        this.vehicleRegNumber = vehicleRegNumber;
        this.status = status;
        this.fare = fare;
        this.goodsType = goodsType;
    }

    // Getters
    public Integer getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public String getPickupDest() { return pickupDest; }
    public String getDropDest() { return dropDest; }
    public String getClientName() { return clientName; }
    public String getClientContact() { return clientContact; }

    public Integer getDriverId() { return driverId; }
    public String getDriverName() { return driverName; }
    public String getDriverRole() { return driverRole; }
    public String getDriverImage() { return driverImage; }

    public Integer getHelperId() { return helperId; }
    public String getHelperName() { return helperName; }
    public String getHelperRole() { return helperRole; }
    public String getHelperImage() { return helperImage; }

    public Integer getVehicleId() { return vehicleId; }
    public String getVehicleRegNumber() { return vehicleRegNumber; }
    public String getStatus() { return status; }
    public Integer getFare() { return fare; }
    public String getGoodsType() { return goodsType; }
}
